package com.amps.policy.config.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amps.policy.config.dao.ProceduresDao;
import com.amps.policy.config.dao.StageProceduresDao;
import com.amps.policy.config.dto.ProceduresDTO;
import com.amps.policy.config.service.StageService;
import com.amps.policy.config.util.DateUtil;
import com.amps.policy.config.util.EmailUtil;
import com.amps.policy.config.util.SharePointUtil;

import java.util.concurrent.CompletableFuture;

import static com.amps.policy.config.constants.ParameterConstants.*;

@Service
public class ProceduresStageServiceImpl implements StageService {

	Logger logger = LogManager.getLogger(ProceduresStageServiceImpl.class);

	@Autowired
	StageProceduresDao stageProceduresDao;

	@Autowired
	SharePointUtil sharePointUtil;
	@Autowired
	ProceduresDao proceduresDao;

	@Autowired
	EmailUtil emailUtil;

	@Autowired
	DateUtil dateUtil;

	public Integer saveToDatabase(MultipartFile file) {
		try {
			logger.info(" Parsing the uploaded file ");
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			List<ProceduresDTO> proceduresDTO = new ArrayList<ProceduresDTO>();
			for (int r = 1; r <= rows; r++) {
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				if (row == null || row.getCell(0) == null)
					break;

				DataFormatter dataFormatter = new DataFormatter();
				ProceduresDTO stage = new ProceduresDTO();

				stage.setPolicyId((int) row.getCell(0).getNumericCellValue());
				stage.setCptFrom(dataFormatter.formatCellValue(row.getCell(1)).trim());
				stage.setCptTo(dataFormatter.formatCellValue(row.getCell(2)).trim());
				stage.setMod1(dataFormatter.formatCellValue(row.getCell(3)).trim());
				stage.setMod2(dataFormatter.formatCellValue(row.getCell(4)).trim());
				stage.setMod3(dataFormatter.formatCellValue(row.getCell(5)).trim());
				stage.setDaysLo((int) row.getCell(6).getNumericCellValue());
				stage.setDaysHi((int) row.getCell(7).getNumericCellValue());
				stage.setRevFrom(dataFormatter.formatCellValue(row.getCell(8)).trim());
				stage.setRevTo(dataFormatter.formatCellValue(row.getCell(9)).trim());
				stage.setPos(dataFormatter.formatCellValue(row.getCell(10)).trim());
				stage.setDosFrom(dateUtil.getFormattedDate(dataFormatter.formatCellValue(row.getCell(11)).trim()));
				stage.setDosTo(dateUtil.getFormattedDate(dataFormatter.formatCellValue(row.getCell(12)).trim()));
				stage.setPolicyCptActionKey((int) row.getCell(13).getNumericCellValue());
				stage.setExcludeb((int) row.getCell(14).getNumericCellValue());
				stage.setDxLink((int) row.getCell(15).getNumericCellValue());
				stage.setClaimLinkKey((int) row.getCell(16).getNumericCellValue());
				if (stage.getDosFrom() == null || stage.getDosTo() == null) {
					stage.setDosFrom(dateUtil.getFormattedDate(row.getCell(11).getRawValue()));
					stage.setDosTo(dateUtil.getFormattedDate(row.getCell(12).getRawValue()));
				}
				proceduresDTO.add(stage);
			}
			Integer policyId = proceduresDTO.get(0).getPolicyId();
			logger.info(" Parsed the uploaded file ");
			workbook.close();
			logger.info(" Deleting data from stage procedures for policy id " + policyId);
			stageProceduresDao.deleteAllData();
			logger.info(" Deleted data from stage procedures for policy id " + policyId);
			logger.info(" Saving data to stage procedures for policy id " + policyId);
			stageProceduresDao.saveData(proceduresDTO);
			logger.info(" Saved data to stage procedures for policy id " + policyId);
			return proceduresDTO.get(0).getPolicyId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String readFileAndSaveFile(MultipartFile file, String email) {
		Integer policyId = saveToDatabase(file);
		if (policyId == 0)
			return null;
		logger.info(" Uploading source file to sharePoint ");
		String sharePointSourcePath = uploadToSharePoint(file, policyId);
		if (sharePointSourcePath == null)
			return null;
		return getDeltaReport(policyId, sharePointSourcePath, email);
	}

	@Override
	public String uploadToSharePoint(MultipartFile file, Integer policyId) {
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

	@Override
	public void loadDataToTarget(Integer policyId) {
		try {
			logger.info(" Loading stage procedures data to policy procedures for policy id " + policyId);
			List<ProceduresDTO> proceduresData = proceduresDao.findByPolicyId(policyId);
			if (proceduresData.size() > 0)
				proceduresDao.deleteByPolicyId(policyId);
			proceduresDao.saveAllStageData(policyId);
			logger.info(" Loaded data to policy procedures for policy id " + policyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getDeltaReport(Integer policyId, String sourcePath, String email) {
		logger.info("Generating delta report for policy procedures for policy id " + policyId);

		String sharePointDeltaPath = sharePointUtil.getSharePointDeltaFolderPath(sourcePath, "DELTA");
		boolean result = stageProceduresDao.generateDeltaReport(policyId, sharePointDeltaPath);
		final String templateName;
		final String deltaLink;
		String sourceLink = sharePointUtil.getSharePointLink(sourcePath);

		if (result) {
			templateName = PROCS_DELTA_REPORT;
			deltaLink = sharePointUtil.getSharePointDeltaAndExceptionLink(sharePointDeltaPath);
			logger.info("Generated delta report and saved to SharePoint");
		} else {
			templateName = PROCS_DELTA_REPORT_FAILURE;
			deltaLink = ""; // or set a default value if needed
			logger.info("Procedures delta generation failed for policy id " + policyId);
		}

		CompletableFuture.runAsync(() -> {
			emailUtil.sendNotificationEmail(templateName, policyId, email, sourceLink, deltaLink, POLICY_PROCEDURES);
		});

		return deltaLink;
	}
}
