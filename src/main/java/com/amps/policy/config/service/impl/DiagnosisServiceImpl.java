package com.amps.policy.config.service.impl;

import com.amps.policy.config.dao.DiagnosisDao;
import com.amps.policy.config.dto.DiagnosisDTO;
import com.amps.policy.config.model.Diagnosis;
import com.amps.policy.config.model.PolicyActionDiagnosisLookUp;
import com.amps.policy.config.repository.DiagnosisRepository;
import com.amps.policy.config.repository.PolicyActionDiagnosisLookUpRepository;
import com.amps.policy.config.service.DiagnosisService;
import com.amps.policy.config.util.DateUtil;
import com.amps.policy.config.util.SharePointUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

	Logger logger = LogManager.getLogger(DiagnosisServiceImpl.class);

	@Autowired
	DateUtil dateUtil;

	@Autowired
	DiagnosisDao diagnosisDao;

	@Autowired
	DiagnosisRepository diagnosisRepository;

	@Autowired
	PolicyActionDiagnosisLookUpRepository PolicyActionDiagnosisLookUpRepository;

	@Autowired
	SharePointUtil sharePointUtil;

	@Override
	public String readFileAndSaveFile(MultipartFile file, String email) {
		Integer policyId = saveToDatabase(file);
		if (policyId == 0)
			return null;
		logger.info(" Uploading source file to sharepoint ");
		String sharePointSourcePath = uploadToSharepoint(file, policyId);
		if (sharePointSourcePath == null)
			return null;
		return sharePointSourcePath;
	}

	@Override
	public Integer saveToDatabase(MultipartFile file) {
		try {
			logger.info(" Parsing the uploaded file ");
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();

			List<DiagnosisDTO> DiagnosisDTO = new ArrayList<DiagnosisDTO>();
			for (int r = 1; r <= rows; r++) {
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				if (row == null || row.getCell(0) == null)
					break;
				XSSFCell cell = row.getCell((short) 3);
				XSSFCell cell1 = row.getCell((short) 4);
				short x = workbook.createDataFormat().getFormat("yyyy-m-dd;@");
				CellStyle dateCellFormat = workbook.createCellStyle();
				String cellValue = (row.getCell(5).getCellType() == CellType.STRING)
						? row.getCell(5).getStringCellValue()
						: Integer.toString((int) row.getCell(5).getNumericCellValue());
				dateCellFormat.setDataFormat(x);
				cell.setCellStyle(dateCellFormat);
				cell1.setCellStyle(dateCellFormat);
				DataFormatter dataFormatter = new DataFormatter();
				DiagnosisDTO stage = new DiagnosisDTO();
				stage.setPolicyId((int) row.getCell(0).getNumericCellValue());
				stage.setDiagFrom(dataFormatter.formatCellValue(row.getCell(1)).trim().replace(".", ""));
				stage.setDiagTo(dataFormatter.formatCellValue(row.getCell(2)).trim().replace(".", ""));
				stage.setDosFrom(
						dateUtil.getFormattedDate(dataFormatter.formatCellValue(row.getCell(3)).toString().trim()));
				stage.setDosTo(
						dateUtil.getFormattedDate(dataFormatter.formatCellValue(row.getCell(4)).toString().trim()));
				if ("BWOR".equalsIgnoreCase(cellValue)) {
					stage.setAction(1);
				} else if ("BOAN".equalsIgnoreCase(cellValue)) {
					stage.setAction(2);
				} else {
					stage.setAction(Integer.parseInt(cellValue));
				}
				stage.setExclusion((int) row.getCell(6).getNumericCellValue());
				stage.setHeaderLevel((int) row.getCell(7).getNumericCellValue());
				stage.setPrincipalDx((int) row.getCell(8).getNumericCellValue());
				stage.setOnlyDx((int) row.getCell(9).getNumericCellValue());
				DiagnosisDTO.add(stage);
			}

			Integer policyId = DiagnosisDTO.get(0).getPolicyId();
			logger.info(" Parsed the uploaded file ");
			workbook.close();
			logger.info(" Deleting data from policyDx for policy id " + policyId);
			diagnosisDao.deleteDxData(policyId);
			logger.info(" Deleted data from policyDx for policy id " + policyId);
			logger.info(" Saving data to policyDx for policy id " + policyId);
			diagnosisDao.saveData(DiagnosisDTO);
			logger.info(" Saved data to policyDx for policy id " + policyId);
			return DiagnosisDTO.get(0).getPolicyId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String uploadToSharepoint(MultipartFile file, Integer policyId) {

		try {
			String sharePointSourcePath = sharePointUtil.getSharePointSourcePath(policyId,  "Policy Procedures");
			File newFile = new File(System.getProperty("java.io.tmpdir") + File.separator + file.getOriginalFilename());
			OutputStream os = new FileOutputStream(newFile);
			os.write(file.getBytes());
			os.close();
			sharePointUtil.readAndUploadFileToSharePoint(newFile, sharePointSourcePath);
			return sharePointSourcePath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteDiagnosisData(int id) {
		diagnosisDao.deleteData(id);
	}

	public void updateDiagnosisData(Diagnosis diagnosis) {
		diagnosisDao.updateData(diagnosis);
	}

	public void updateDiagHeaders(Diagnosis diagnosis){
		diagnosisDao.updateDiagHeaders(diagnosis);
	}

	public List<Diagnosis> getDiagnosisData(int policy_id) {
		return diagnosisRepository.getDiagnosisData(policy_id);
	}

	public void saveDiagnosisData(Diagnosis diagnosis) {
		diagnosisRepository.save(diagnosis);
	}

	@Override
	public void saveDiagnosisListData(List<Diagnosis> diagnosis) {
		diagnosisDao.saveDiagsData(diagnosis);
	}

	public List<DiagnosisDTO> ExportDiagnosisData(int id) {
		return diagnosisDao.getDxExportById(id);
	}

	@Override
	public List<PolicyActionDiagnosisLookUp> getPolicyActionDiagnosisLkpData() {
		return PolicyActionDiagnosisLookUpRepository.findAll();
	}
}
