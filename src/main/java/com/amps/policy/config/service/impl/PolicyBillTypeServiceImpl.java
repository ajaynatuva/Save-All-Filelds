package com.amps.policy.config.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amps.policy.config.dao.PolicyBillTypeDao;
import com.amps.policy.config.dto.PolicyBillTypeDTO;
import com.amps.policy.config.model.BillTypeLookUp;
import com.amps.policy.config.model.Policy;
import com.amps.policy.config.model.PolicyActionBillTypeLookUp;
import com.amps.policy.config.model.PolicyBillType;
import com.amps.policy.config.model.PolicyBillTypeLinkLookUp;
import com.amps.policy.config.repository.BillTypeLinkLkpRepository;
import com.amps.policy.config.repository.BillTypeLookUpRepository;
import com.amps.policy.config.repository.PolicyActionBillTypeLookUpRepository;
import com.amps.policy.config.repository.PolicyBillTypeRepository;
import com.amps.policy.config.repository.PolicyRepository;
import com.amps.policy.config.service.PolicyBillTypeService;

@Service
public class PolicyBillTypeServiceImpl implements PolicyBillTypeService {

	Logger logger = LogManager.getLogger(PolicyBillTypeServiceImpl.class);

	@Autowired
	PolicyBillTypeDao policyBillTypeDao;

	@Autowired
	BillTypeLinkLkpRepository BillTypeLinkLkpRepository;

	@Autowired
	PolicyActionBillTypeLookUpRepository PolicyActionBillTypeLookUpRepository;

	@Autowired
	PolicyBillTypeRepository PolicyBillTypeRepository;

	@Autowired
	BillTypeLookUpRepository BillTypeLookUpRepository;

	@Autowired
	PolicyRepository policyRepository;

	@Override
	public String readFileAndSaveFile(MultipartFile file) {
		Integer policyId = saveToDatabase(file);
		return policyId.toString();
	}

	@Override
	public Integer saveToDatabase(MultipartFile file) {
		try {
			logger.info(" Parsing the uploaded file ");
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			List<PolicyBillTypeDTO> policyBillTypeDTO = new ArrayList<PolicyBillTypeDTO>();
			List<PolicyBillTypeDTO> deletePolicyDuplicates = new ArrayList<PolicyBillTypeDTO>();
			for (int r = 1; r <= rows; r++) {
				PolicyBillTypeDTO billType = new PolicyBillTypeDTO();
				XSSFRow row = (XSSFRow) sheet.getRow(r);
				if (row == null || row.getCell(0) == null)
					break;
				DataFormatter dataFormatter = new DataFormatter();
				billType.setPolicyId((int) row.getCell(0).getNumericCellValue());
				billType.setBillType(dataFormatter.formatCellValue(row.getCell(1)).trim());
				billType.setBillTypeDesc(dataFormatter.formatCellValue(row.getCell(2)).trim());
				policyBillTypeDTO.add(billType);
				deletePolicyDuplicates.add(billType);
			}

			logger.info(" Parsed the uploaded file ");
			workbook.close();
			policyBillTypeDao.deleteBillTypeByPolicyId(deletePolicyDuplicates);
			policyBillTypeDao.saveData(policyBillTypeDTO);
			return  policyBillTypeDTO.get(0).getPolicyId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<BillTypeLookUp> getBillTypeLkpData() {
		// TODO Auto-generated method stub
		return BillTypeLookUpRepository.findAll();
	}

	@Override
	public List<PolicyBillTypeLinkLookUp> getBillTypeLinkLkpData() {
		// TODO Auto-generated method stub
		return BillTypeLinkLkpRepository.findAll();
	}

	@Override
	public List<PolicyBillType> getPolicyBillTypeDataById(int policy_id) {
		// TODO Auto-generated method stub
		return PolicyBillTypeRepository.getBillTypeData(policy_id);
	}

	@Override
	public List<PolicyActionBillTypeLookUp> getPolicyBillTypeActionData() {
		// TODO Auto-generated method stub
		return PolicyActionBillTypeLookUpRepository.findAll();
	}

	@Override
	public void savePolicyBillTypeData(List<PolicyBillType> policyBillType) {
		// TODO Auto-generated method stub
		policyBillTypeDao.savePolicyBillType(policyBillType);
	}

	@Override
	public void deletePolicyBillTypeData(int key) {
		// TODO Auto-generated method stub
		policyBillTypeDao.deletePolicyBillType(key);
	}

	@Override
	public Policy getBillTypeByPolicyId(int id) {
		// TODO Auto-generated method stub
		return policyRepository.findByPolicyId(id);
	}
}
