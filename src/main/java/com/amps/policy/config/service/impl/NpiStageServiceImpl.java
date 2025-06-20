package com.amps.policy.config.service.impl;

import static com.amps.policy.config.constants.ParameterConstants.IMPORT_DELTA_REPORT;
import static com.amps.policy.config.constants.ParameterConstants.IMPORT_DELTA_REPORT_FAILURE;
import static com.amps.policy.config.constants.ParameterConstants.IMPORT_STAGE_FAILED;
import static com.amps.policy.config.constants.ParameterConstants.NPI;

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

import com.amps.policy.config.dao.NpiDao;
import com.amps.policy.config.dto.NpiDTO;
import com.amps.policy.config.service.NpiStageService;
import com.amps.policy.config.util.EmailUtil;
import com.amps.policy.config.util.SharePointUtil;

@Service
public class NpiStageServiceImpl implements NpiStageService {

	Logger logger = LogManager.getLogger(NpiStageServiceImpl.class);

	@Autowired
	NpiDao npiDao;

	@Autowired
	SharePointUtil sharePointUtil;

	@Autowired
	EmailUtil emailUtil;

	@Override
	public String readFileAndSaveFile(MultipartFile file, String email) {
		// TODO Auto-generated method stub

		Integer policyId = saveToDatabase(file, email);
		if (policyId == 0) {
			return null;
		}
		logger.info(" Uploading source file to sharePoint ");
		String sharePointSourcePath = uploadToSharePoint(file, policyId);
		if (sharePointSourcePath == null)
			return null;

		return getDeltaReport(policyId, sharePointSourcePath, email);

	}

	public Integer saveToDatabase(MultipartFile file, String email) {
		String errorMessage = "";

		try {
			logger.info("Parsing the uploaded file");
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			List<NpiDTO> npiDTO = new ArrayList<>();
			for (int i = 1; i <= rows; i++) {
				XSSFRow row = (XSSFRow) sheet.getRow(i);
				if (row == null || row.getCell(0) == null)
					break;

				DataFormatter dataFormatter = new DataFormatter();
				NpiDTO stage = new NpiDTO();

				stage.setPolicyId((int) row.getCell(0).getNumericCellValue());
				stage.setClientGroupId(((int) row.getCell(1).getNumericCellValue()));
				stage.setLob(dataFormatter.formatCellValue(row.getCell(4)).trim());
				stage.setClaimType(dataFormatter.formatCellValue(row.getCell(5)).trim());
				stage.setNpi((long) row.getCell(6).getNumericCellValue());
				String action = dataFormatter.formatCellValue(row.getCell(7)).trim();
				if(action.equalsIgnoreCase("Active")){
					stage.setDeletedB(0);
				}else if(action.equalsIgnoreCase("Deactivated")){
					stage.setDeletedB(1);
				}

				npiDTO.add(stage);
			}
			logger.info("Parsed the uploaded file");
			workbook.close();
			logger.info(" Deleting data from stage and delta NPI ");
			npiDao.deleteStageData(npiDTO.getFirst().getPolicyId());
			npiDao.deleteDeltaData(npiDTO.getFirst().getPolicyId());
			logger.info(" Deleted data from stage NPI");
			logger.info(" Saving data to stage NPI ");
			npiDao.loadDataToStage(npiDTO);
			logger.info(" Saved data to stage NPI ");
			return npiDTO.getFirst().getPolicyId();
		} catch (NullPointerException e) {
			errorMessage = "Null values in file";
			emailUtil.sendNotificationEmailForStageLoadFailure(IMPORT_STAGE_FAILED, email, errorMessage, "NPI");
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "Server Error";
			emailUtil.sendNotificationEmailForStageLoadFailure(IMPORT_STAGE_FAILED, email, errorMessage, "NPI");
		}
		return 0;

	}

	public String getDeltaReport(Integer policyId, String sourcePath, String email) {
		boolean result = false;
		String sharePointDeltaPath = sharePointUtil.getSharePointDeltaFolderPath(sourcePath, "DELTA");
		String sharePointExceptionPath = sharePointUtil.getSharePointDeltaFolderPath(sourcePath, "EXCEPTION");
		boolean ErrorResult = npiDao.generateExceptionReport(policyId, sharePointExceptionPath);
		if(!ErrorResult) {
			logger.info("Generating delta report for Npi for policy id " + policyId);
			result = npiDao.generateDeltaReport(policyId, sharePointDeltaPath);
		}
		final String templateName;
		final String deltaLink;
		String sourceLink = sharePointUtil.getSharePointLink(sourcePath);
		final String emailDeltaLink; // Local variable for email-specific deltaLink


		if (result) {
			templateName = IMPORT_DELTA_REPORT;
			deltaLink = sharePointUtil.getSharePointDeltaAndExceptionLink(sharePointDeltaPath);
			emailDeltaLink = deltaLink;
			logger.info("Generated delta report and saved to SharePoint");
		} else {
			templateName = IMPORT_DELTA_REPORT_FAILURE;
			emailDeltaLink = sharePointUtil.getSharePointDeltaAndExceptionLink(sharePointExceptionPath); // or set a default
			deltaLink = null;																					// value if needed
			logger.info("Npi delta generation failed for policy id " + policyId);
		}

		CompletableFuture.runAsync(() -> {
			emailUtil.sendNotificationEmail(templateName, policyId, email, sourceLink, emailDeltaLink, NPI);
		});

		return deltaLink;
	}

	public String uploadToSharePoint(MultipartFile file, Integer policyId) {
		try {
			String sharePointSourcePath = sharePointUtil.getSharePointSourcePath(policyId, "Npi");
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
}
