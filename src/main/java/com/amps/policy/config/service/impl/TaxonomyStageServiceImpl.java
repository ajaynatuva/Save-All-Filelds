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

import com.amps.policy.config.dao.TaxonomyDao;
import com.amps.policy.config.dto.TaxonomyDTO;
import com.amps.policy.config.model.Taxonomy;
import com.amps.policy.config.service.TaxonomyService;
import com.amps.policy.config.util.EmailUtil;
import com.amps.policy.config.util.SharePointUtil;

import static com.amps.policy.config.constants.ParameterConstants.*;

@Service
public class TaxonomyStageServiceImpl {
	Logger logger = LogManager.getLogger(TaxonomyStageServiceImpl.class);
	@Autowired
	SharePointUtil sharePointUtil;
	@Autowired
	EmailUtil emailUtil;
	@Autowired
	TaxonomyDao taxonomyDao;

	@Autowired
	TaxonomyService taxonomyService;

	public String readFileAndSaveFile(MultipartFile file, String email) {
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

	public String uploadToSharePoint(MultipartFile file, Integer policyId) {
		try {
			String sharePointSourcePath = sharePointUtil.getSharePointSourcePath(policyId, "Taxonomy");
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


	public Integer saveToDatabase(MultipartFile file, String email) {
		String errorMessage = "";

		try {
			logger.info(" Parsing the uploaded file ");
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			List<TaxonomyDTO> taxonomyDTO = new ArrayList<>();
			for (int r = 1; r <= rows; r++) {
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				if (row == null || row.getCell(0) == null)
					break;

				DataFormatter dataFormatter = new DataFormatter();
				TaxonomyDTO stage = new TaxonomyDTO();
				stage.setPolicyId((int) row.getCell(0).getNumericCellValue());
				stage.setClientGroupId((int) row.getCell(1).getNumericCellValue());
				stage.setSpecCode(dataFormatter.formatCellValue(row.getCell(4)).trim());
				stage.setSubSpecCode(dataFormatter.formatCellValue(row.getCell(5)).trim());
				stage.setSubSpecDesc(dataFormatter.formatCellValue(row.getCell(6)).trim());
				stage.setTaxonomyCode(dataFormatter.formatCellValue(row.getCell(7)).trim());
				String function = dataFormatter.formatCellValue(row.getCell(8)).trim();
				if (function.equalsIgnoreCase("Applies To")) {
					stage.setFunction(1);
				} else if (function.equalsIgnoreCase("Exclude")) {
					stage.setFunction(0);
				}
				String action = dataFormatter.formatCellValue(row.getCell(9)).trim();
				if(action.equalsIgnoreCase("Active")){
					stage.setDeletedB(0);
				}else if(action.equalsIgnoreCase("Deactivated")){
					stage.setDeletedB(1);
				}
				taxonomyDTO.add(stage);
			}
			logger.info(" Parsed the uploaded file ");
			workbook.close();
			logger.info(" Deleting data from stage taxonomy ");
//            taxonomyDao.deleteAllData();
			taxonomyDao.deleteStageData(taxonomyDTO.getFirst().getPolicyId());
			taxonomyDao.deleteDeltaData(taxonomyDTO.getFirst().getPolicyId());
			logger.info(" Deleted data from stage taxonomy ");
			logger.info(" Saving data to stage taxonomy ");
			taxonomyDao.saveData(taxonomyDTO);
			logger.info(" Saved data to stage taxonomy ");
			return taxonomyDTO.getFirst().getPolicyId();
		} catch (NullPointerException e) {
			errorMessage = "Null values in file";
			emailUtil.sendNotificationEmailForStageLoadFailure(IMPORT_STAGE_FAILED, email, errorMessage , "Taxonomy");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	public List<Taxonomy> loadDataToTarget(Integer policyId) {
		List<Taxonomy> result = new ArrayList<>();
		List<Taxonomy> deleteActionTaxonomyData;
		try {
			logger.info(" Loading stage Taxonomy data to Taxonomy for policy id " + policyId);
			List<Taxonomy> taxonomyDTO = taxonomyService.getTaxonomyByPolicyId(policyId);
			if (!taxonomyDTO.isEmpty()){
				deleteActionTaxonomyData = taxonomyDao.getAllDeleteActionTaxonomyData(policyId);
				taxonomyDao.deleteTaxonomyData(deleteActionTaxonomyData);
			}
			taxonomyDao.saveAllStageData(policyId);
			logger.info(" Loaded data to Taxonomy for policy id " + policyId);

			result = taxonomyService.getTaxonomyByPolicyId(policyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getDeltaReport(Integer policyId, String sourcePath, String email) {
		boolean result = false;
		String sharePointDeltaPath = sharePointUtil.getSharePointDeltaFolderPath(sourcePath, "DELTA");
		String sharePointExceptionPath = sharePointUtil.getSharePointDeltaFolderPath(sourcePath, "EXCEPTION");
		boolean ErrorResult = taxonomyDao.generateExceptionReport(policyId, sharePointExceptionPath);
		if (!ErrorResult) {
			logger.info("Generating delta report for Taxonomy for policy id " + policyId);
			result = taxonomyDao.generateDeltaReport(policyId, sharePointDeltaPath);
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
			deltaLink = null;                                                                                    // value if needed
			logger.info("Taxonomy delta generation failed for policy id " + policyId);
		}

		CompletableFuture.runAsync(() -> {
			emailUtil.sendNotificationEmail(templateName, policyId, email, sourceLink, emailDeltaLink, TAXONOMY);
		});

		return deltaLink;
	}
}
