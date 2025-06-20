package com.amps.policy.config.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amps.policy.config.model.BillTypeLookUp;
import com.amps.policy.config.model.ConditionCodeLookUp;
import com.amps.policy.config.model.MinMaxAgeLookUp;
import com.amps.policy.config.model.ModLookUp;
import com.amps.policy.config.model.PolicyCategoryLookUp;
import com.amps.policy.config.model.PosLookUp;
import com.amps.policy.config.model.ReasonCodeLookUp;
import com.amps.policy.config.model.RevenueCodeLookUp;
import com.amps.policy.config.model.SpecLookUp;
import com.amps.policy.config.model.SubSpecMap;
import com.amps.policy.config.repository.BillTypeLookUpRepository;
import com.amps.policy.config.repository.ConditionCodeLookUpRepository;
import com.amps.policy.config.repository.MinMaxAgeLookUpRepository;
import com.amps.policy.config.repository.ModLookUpRepository;
import com.amps.policy.config.repository.PolicyCategoryLookUpRepository;
import com.amps.policy.config.repository.PosLookUpRepository;
import com.amps.policy.config.repository.ReasonCodeLookUpRepository;
import com.amps.policy.config.repository.RevenueCodeLookUpRepository;
import com.amps.policy.config.repository.SpecLookUpRepository;
import com.amps.policy.config.repository.SubSpecLookUpRepository;
import com.amps.policy.config.service.UploadLookUpService;

@Component
public class UploadLookUpServiceImpl implements UploadLookUpService {

	@Autowired
	SpecLookUpRepository specLookUpRepository;

	@Autowired
	RevenueCodeLookUpRepository revenueCodeRepository;

	@Autowired
	ModLookUpRepository modLookUpRepository;

	@Autowired
	PosLookUpRepository posLookUpRepository;

	@Autowired
	PolicyCategoryLookUpRepository policyCatLookUpRepository;

	@Autowired
	ReasonCodeLookUpRepository reasonCodeLookUpRepository;

	@Autowired
	SubSpecLookUpRepository subSpecLookUpRepository;

	@Autowired
	BillTypeLookUpRepository billTypeLookUpRepository;
	
	@Autowired
	ConditionCodeLookUpRepository conditionCodeRepository;

	@Autowired
	MinMaxAgeLookUpRepository minMaxAgeLookUpRepository;
	
	@Override
	public void saveToDatabase(MultipartFile file, String className) {
		// TODO Auto-generated method stub

		try {
			switch (className) {
			case "SpecsLookUp" -> {
				List<SpecLookUp> specLkp = specLookUp(file.getInputStream());
				specLookUpRepository.saveAll(specLkp);
				break;
			}
			case "SubSpecMap" -> {
				List<SubSpecMap> subSpecLkp = subSpecMap(file.getInputStream());
				subSpecLookUpRepository.saveAll(subSpecLkp);
				break;
			}
			case "RevenueCodeLookUp" -> {
				List<RevenueCodeLookUp> revenueCodeLkp = revCodeLookUp(file.getInputStream());
				revenueCodeRepository.saveAll(revenueCodeLkp);
				break;
			}
			case "ConditionCodeLookUp" -> {
				List<ConditionCodeLookUp> conditionCodeLkp = condCodeLookUp(file.getInputStream());
				conditionCodeRepository.saveAll(conditionCodeLkp);
				break;
			}
			case "BillTypeLookUp" -> {
				List<BillTypeLookUp> billTypeLkp = billTypeLookUp(file.getInputStream());
				billTypeLookUpRepository.saveAll(billTypeLkp);
				break;
			}
			case "MinMaxAgeLookUp" -> {
				List<MinMaxAgeLookUp> minMaxAgeLkp = minMaxLookUp(file.getInputStream());
				minMaxAgeLookUpRepository.saveAll(minMaxAgeLkp);
				break;
			}
			case "ModLookUp" -> {
				List<ModLookUp> modLookUp = modLookUp(file.getInputStream());
				modLookUpRepository.saveAll(modLookUp);
				break;
			}
			case "PosLookUp" -> {
				List<PosLookUp> posLkp = posLookUp(file.getInputStream());
				posLookUpRepository.saveAll(posLkp);
				break;
			}
			case "PolicyCatLookUp" -> {
				List<PolicyCategoryLookUp> policyCatLkp = policyCatLookUp(file.getInputStream());
				policyCatLookUpRepository.saveAll(policyCatLkp);
				break;
			}
			case "ReasonCodeLookUp" -> {
				List<ReasonCodeLookUp> reasonCodeLkp = reasonCodeLookUp(file.getInputStream());
				reasonCodeLookUpRepository.saveAll(reasonCodeLkp);
				break;
			}
			default -> {
				throw new IllegalArgumentException("Invalid type: " + className);
			}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<SpecLookUp> specLookUp(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheetAt(0);

			List<SpecLookUp> specDetails = new ArrayList<SpecLookUp>();

			int rows = sheet.getLastRowNum();
			for (int r = 1; r <= rows; r++) {
				SpecLookUp spec = new SpecLookUp();
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				spec.setSpecCode(row.getCell(0).getStringCellValue());
				spec.setSpecDesc(row.getCell(1).getStringCellValue());
				specDetails.add(spec);
			}
			workbook.close();

			return specDetails;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<SubSpecMap> subSpecMap(InputStream is) {

		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheetAt(0);
			List<SubSpecMap> subSpecDetails = new ArrayList<SubSpecMap>();

			int rows = sheet.getLastRowNum();
			for (int r = 1; r <= rows; r++) {
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				SubSpecMap subSpec = new SubSpecMap();

				subSpec.setSubSpecCode(row.getCell(0).getStringCellValue());
				subSpec.setSpecCode(row.getCell(1).getStringCellValue());
				subSpec.setSubSpecDesc(row.getCell(2).getStringCellValue());
				subSpec.setMiscB((int) row.getCell(3).getNumericCellValue());
				subSpecDetails.add(subSpec);
			}

			workbook.close();
			return subSpecDetails;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<RevenueCodeLookUp> revCodeLookUp(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheetAt(0);

			List<RevenueCodeLookUp> revDetails = new ArrayList<RevenueCodeLookUp>();

			int rows = sheet.getLastRowNum();
			for (int r = 1; r <= rows; r++) {
				RevenueCodeLookUp revenueCodeLk = new RevenueCodeLookUp();
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				revenueCodeLk.setRevCode(row.getCell(0).getStringCellValue());
				revenueCodeLk.setRevDesc(row.getCell(1).getStringCellValue());
				revDetails.add(revenueCodeLk);
			}
			// Close WorkBook
			workbook.close();

			return revDetails;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ReasonCodeLookUp> reasonCodeLookUp(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheetAt(0);
			List<ReasonCodeLookUp> reasonDetails = new ArrayList<ReasonCodeLookUp>();
			int rows = sheet.getLastRowNum();
			for (int r = 1; r <= rows; r++) {
				ReasonCodeLookUp reason = new ReasonCodeLookUp();
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				reason.setReasonCode(row.getCell(0).getStringCellValue());
				reason.setReasonDesc(row.getCell(1).getStringCellValue());
				reason.setChallengeCode(row.getCell(2).getStringCellValue());
				reason.setChallengeDesc(row.getCell(3).getStringCellValue());
				reason.setPcoCode(row.getCell(4).getStringCellValue());
				reason.setHipaaCode(row.getCell(5).getStringCellValue());
				reason.setHippaDesc(row.getCell(6).getStringCellValue());
				reason.setDeactivatedb((int) row.getCell(7).getNumericCellValue());
				reason.setCustomb((int) row.getCell(8).getNumericCellValue());

				reasonDetails.add(reason);
			}
			workbook.close();

			return reasonDetails;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<BillTypeLookUp> billTypeLookUp(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheetAt(0);
			List<BillTypeLookUp> billDetails = new ArrayList<BillTypeLookUp>();
			int rows = sheet.getLastRowNum();
			for (int r = 1; r <= rows; r++) {
				BillTypeLookUp bill = new BillTypeLookUp();
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				bill.setBillType(row.getCell(0).getStringCellValue());
				bill.setBillTypeDesc(row.getCell(1).getStringCellValue());
				bill.setInpatientB((int) row.getCell(2).getNumericCellValue());
				bill.setStartDate(row.getCell(3).getDateCellValue());
				bill.setEndDate(row.getCell(4).getDateCellValue());

				billDetails.add(bill);
			}
			workbook.close();

			return billDetails;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ModLookUp> modLookUp(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheetAt(0);
			List<ModLookUp> modDetails = new ArrayList<ModLookUp>();
			int rows = sheet.getLastRowNum();
			for (int r = 1; r <= rows; r++) {
				ModLookUp mod = new ModLookUp();
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				mod.setCptMod(row.getCell(0).getStringCellValue());
				mod.setDescription(row.getCell(1).getStringCellValue());
				mod.setAmbulanceModB((int) row.getCell(2).getNumericCellValue());
				mod.setStartDate(row.getCell(3).getDateCellValue());
				mod.setEndDate(row.getCell(4).getDateCellValue());
				modDetails.add(mod);
			}
			workbook.close();

			return modDetails;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<PosLookUp> posLookUp(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheetAt(0);

			List<PosLookUp> posDetails = new ArrayList<PosLookUp>();

			int rows = sheet.getLastRowNum();
			for (int r = 1; r <= rows; r++) {
				PosLookUp pos = new PosLookUp();
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				pos.setPosCode(row.getCell(0).getStringCellValue());
				pos.setPosName(row.getCell(1).getStringCellValue());
				pos.setPosDesc(row.getCell(2).getStringCellValue());
				pos.setFacilityB((int) row.getCell(3).getNumericCellValue());
				posDetails.add(pos);
			}
			workbook.close();

			return posDetails;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	
	public List<ConditionCodeLookUp> condCodeLookUp(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheetAt(0);

			List<ConditionCodeLookUp> condDetails = new ArrayList<ConditionCodeLookUp>();

			int rows = sheet.getLastRowNum();
			for (int r = 1; r <= rows; r++) {
				ConditionCodeLookUp cond = new ConditionCodeLookUp();
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				cond.setCondId((int) row.getCell(0).getNumericCellValue());
				cond.setCondCode(row.getCell(1).getStringCellValue());
				cond.setCondDesc(row.getCell(2).getStringCellValue());
				cond.setPriority((int) row.getCell(3).getNumericCellValue());
				cond.setStartDate(row.getCell(4).getDateCellValue());
				cond.setEndDate(row.getCell(5).getDateCellValue());
				condDetails.add(cond);
			}
			workbook.close();

			return condDetails;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<MinMaxAgeLookUp> minMaxLookUp(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheetAt(0);

			List<MinMaxAgeLookUp> minMaxDetails = new ArrayList<MinMaxAgeLookUp>();

			int rows = sheet.getLastRowNum();
			for (int r = 1; r <= rows; r++) {
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				MinMaxAgeLookUp minMax = new MinMaxAgeLookUp();

				minMax.setMinMaxAgeLkpId((int) row.getCell(0).getNumericCellValue());
				minMax.setMinMaxAgeDesc(row.getCell(1).getStringCellValue());
				minMax.setAgeYears((int) row.getCell(2).getNumericCellValue());
				minMax.setAgeMonths((int) row.getCell(3).getNumericCellValue());
				minMax.setAgeDays((int) row.getCell(4).getNumericCellValue());
				minMax.setEqualsB((int) row.getCell(5).getNumericCellValue());
				minMax.setMinVsMaxB((int) row.getCell(6).getNumericCellValue());
				minMaxDetails.add(minMax);
			}

			workbook.close();

			return minMaxDetails;
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
