package com.amps.policy.config.service.impl;

import com.amps.policy.config.model.PolicyCategoryLookUp;
import com.amps.policy.config.repository.PolicyCategoryLookUpRepository;
import com.amps.policy.config.service.PolicyCatLookUpService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyCategoryLookUpServiceImpl implements PolicyCatLookUpService {

	@Autowired
	PolicyCategoryLookUpRepository policyCatLookUpRepository;

	public void saveToDatabase(MultipartFile file) {
		try {
			List<PolicyCategoryLookUp> policyDet = policyCatLookUp(file.getInputStream());
			policyCatLookUpRepository.saveAll(policyDet);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<PolicyCategoryLookUp> policyCatLookUp(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheetAt(0);

			List<PolicyCategoryLookUp> policyDetails = new ArrayList<PolicyCategoryLookUp>();

			int rows = sheet.getLastRowNum();
			for (int r = 1; r <= rows; r++) {
				PolicyCategoryLookUp policy = new PolicyCategoryLookUp();
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				policy.setPolicyCategoryDesc(row.getCell(0).getStringCellValue());
				policy.setPriority((int) row.getCell(1).getNumericCellValue());
				policy.setHardDenialB((int) row.getCell(2).getNumericCellValue());
				policy.setLastUpdatedAt(row.getCell(3).getDateCellValue());
				policy.setPolicyCategoryLkpId((int) row.getCell(4).getNumericCellValue());				

				policyDetails.add(policy);
			}
			workbook.close();

			return policyDetails;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
