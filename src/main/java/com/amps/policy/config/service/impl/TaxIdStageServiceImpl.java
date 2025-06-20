package com.amps.policy.config.service.impl;

import com.amps.policy.config.dao.TaxIdDao;
import com.amps.policy.config.dto.TaxIdDTO;
import com.amps.policy.config.service.TaxIdStageService;
import com.amps.policy.config.util.EmailUtil;
import com.amps.policy.config.util.SharePointUtil;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.amps.policy.config.constants.ParameterConstants.*;

@Service
public class TaxIdStageServiceImpl implements TaxIdStageService {
    Logger logger = LogManager.getLogger(TaxIdStageServiceImpl.class);
    @Autowired
    SharePointUtil sharePointUtil;
    @Autowired
    EmailUtil emailUtil;

    @Autowired
    TaxIdDao taxIdDao;

    @Override
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

    public Integer saveToDatabase(MultipartFile file, String email) {
        String errorMessage = "";

        try {
            logger.info(" Parsing the uploaded file ");
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            List<TaxIdDTO> taxIdDto = new ArrayList<>();
            for (int r = 1; r <= rows; r++) {
                XSSFRow row = (XSSFRow) sheet.getRow(r);
                if (row == null || row.getCell(0) == null)
                    break;

                DataFormatter dataFormatter = new DataFormatter();
                TaxIdDTO stage = new TaxIdDTO();
                stage.setPolicyId((int) row.getCell(0).getNumericCellValue());
                stage.setClientGroupId((int) row.getCell(1).getNumericCellValue());
                stage.setLob(dataFormatter.formatCellValue(row.getCell(4)).trim());
                stage.setClaimType(dataFormatter.formatCellValue(row.getCell(5)).trim());
                stage.setTaxId((int) row.getCell(6).getNumericCellValue());
                String action = dataFormatter.formatCellValue(row.getCell(7)).trim();
                if(action.equalsIgnoreCase("Active")){
                    stage.setDeletedB(0);
                }else if(action.equalsIgnoreCase("Deactivated")){
                    stage.setDeletedB(1);
                }
                taxIdDto.add(stage);
            }
            logger.info(" Parsed the uploaded file ");
            workbook.close();
            logger.info(" Deleting data from stage and delta TaxID ");
            taxIdDao.deleteStageData(taxIdDto.getFirst().getPolicyId());
            taxIdDao.deleteDeltaData(taxIdDto.getFirst().getPolicyId());
            logger.info(" Deleted data from stage TaxID ");
            logger.info(" Saving data to stage TaxID ");
            taxIdDao.saveData(taxIdDto);
            logger.info(" Saved data to stage TaxID ");
            return taxIdDto.getFirst().getPolicyId();
        }catch (NullPointerException e) {
            errorMessage = "Null values in file";
            emailUtil.sendNotificationEmailForStageLoadFailure(IMPORT_STAGE_FAILED, email, errorMessage, "Tax Id");
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = "Server Error";
            emailUtil.sendNotificationEmailForStageLoadFailure(IMPORT_STAGE_FAILED, email, errorMessage, "Tax Id");
        }
        return 0;
    }

    public String getDeltaReport(Integer policyId, String sourcePath, String email) {
        boolean result = false;
        String sharePointDeltaPath = sharePointUtil.getSharePointDeltaFolderPath(sourcePath, "DELTA");
        String sharePointExceptionPath = sharePointUtil.getSharePointDeltaFolderPath(sourcePath, "EXCEPTION");
        boolean ErrorResult = taxIdDao.generateExceptionReport(policyId, sharePointExceptionPath);
        if(!ErrorResult) {
            logger.info("Generating delta report for Tax Id for policy id " + policyId);
            result = taxIdDao.generateDeltaReport(policyId, sharePointDeltaPath);
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
            logger.info("Tax Id delta generation failed for policy id " + policyId);
        }

        CompletableFuture.runAsync(() -> {
            emailUtil.sendNotificationEmail(templateName, policyId, email, sourceLink, emailDeltaLink, TAX_ID);
        });

        return deltaLink;
    }

    public String uploadToSharePoint(MultipartFile file, Integer policyId) {
        try {
            String sharePointSourcePath = sharePointUtil.getSharePointSourcePath(policyId, "Tax Id");
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
