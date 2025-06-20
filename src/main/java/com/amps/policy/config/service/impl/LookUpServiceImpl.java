package com.amps.policy.config.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.amps.policy.config.dao.LookUpDao;
import com.amps.policy.config.dto.SubSpecDTO;
import com.amps.policy.config.dto.statesDTO;
import com.amps.policy.config.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amps.policy.config.repository.BillTypeLookUpRepository;
import com.amps.policy.config.repository.BoLkpRepository;
import com.amps.policy.config.repository.BwTypeLkpRepository;
import com.amps.policy.config.repository.CciRepository;
import com.amps.policy.config.repository.ClaimTypeLinkLkpRepository;
import com.amps.policy.config.repository.ConditionCodeLookUpRepository;
import com.amps.policy.config.repository.MaxUnitsLookUpRepository;
import com.amps.policy.config.repository.MaxUnitsTypeRepository;
import com.amps.policy.config.repository.MinMaxAgeLookUpRepository;
import com.amps.policy.config.repository.ModLookUpRepository;
import com.amps.policy.config.repository.ModifierInteractionRepository;
import com.amps.policy.config.repository.ModifierInteractionTypeRepository;
import com.amps.policy.config.repository.ModifierPayPercentageLkpRepository;
import com.amps.policy.config.repository.ModifierPayPercentageRepository;
import com.amps.policy.config.repository.ModifierPriorityRepository;
import com.amps.policy.config.repository.MueRationalLkpRepository;
import com.amps.policy.config.repository.PolicyCategoryLookUpRepository;
import com.amps.policy.config.repository.PosLookUpRepository;
import com.amps.policy.config.repository.PtpCciRationaleRepository;
import com.amps.policy.config.repository.ReasonCodeLookUpRepository;
import com.amps.policy.config.repository.RevenueCodeLookUpRepository;
import com.amps.policy.config.repository.SpecLookUpRepository;
import com.amps.policy.config.repository.SubSpecLookUpRepository;
import com.amps.policy.config.repository.CciDeviationRepository;
import com.amps.policy.config.repository.StatesRepository;
import com.amps.policy.config.service.LookUpService;

import com.amps.policy.config.service.UploadLookUpService;
import com.amps.policy.config.util.SharePointUtil;

@Service
public class LookUpServiceImpl implements LookUpService {

	@Autowired
	SpecLookUpRepository specLookUpRepository;

	@Autowired
	RevenueCodeLookUpRepository revenueCodeRepository;

	@Autowired
	ConditionCodeLookUpRepository conditionCodeRepository;

	@Autowired
	MinMaxAgeLookUpRepository minMaxAgeRepository;

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
	MinMaxAgeLookUpRepository minMaxAgeLookUpRepository;

	@Autowired
	RevenueCodeLookUpRepository revenueCodeLookUpRepository;

	@Autowired
	BillTypeLookUpRepository billTypeLookUpRepository;

	@Autowired
	PtpCciRationaleRepository ptpCciRationaleRepository;

	@Autowired
	ClaimTypeLinkLkpRepository claimTypeLinkLkpRepository;

	@Autowired
	CciRepository CciRepository;

	@Autowired
	MueRationalLkpRepository MueRationalLkpRepository;

	@Autowired
	BoLkpRepository BoLkpRepository;

	@Autowired
	BwTypeLkpRepository BwTypeLkpRepository;

	@Autowired
	MaxUnitsTypeRepository MaxUnitsTypeRepository;

	@Autowired
	MaxUnitsLookUpRepository MaxUnitsLookUpRepository;

	@Autowired
	ModifierPriorityRepository modifierPriorityRepository;

	@Autowired
	ModifierInteractionRepository modifierInteractionRepository;

	@Autowired
	ModifierInteractionTypeRepository modifierInteractionTypeRepository;

	@Autowired
	ModifierPayPercentageRepository modifierPayPercentageRepository;

	@Autowired
	ModifierPayPercentageLkpRepository ModifierPayPercentageLkpRepository;

	@Autowired
	CciDeviationRepository CciDeviationRepository;

	@Autowired
	StatesRepository StatesRepository;

	@Autowired
	UploadLookUpService upLoadLookUpService;

	@Autowired
	SharePointUtil sharePointUtil;

	@Autowired
	LookUpDao lookUpDao;
	
	@Autowired
	CciDeviationRepository cciDeviationRepository;

	@Autowired
	NamedParameterJdbcTemplate dragonNamedParameterJdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

	public static String FileSeparator = "/";

	public void readFileAndSaveFile(String className, MultipartFile file) {

		upLoadLookUpService.saveToDatabase(file, className);
	}

	@Override
	public void uploadToSharePoint(String className, MultipartFile file) throws IOException {
		String sharePointSourcePath = sharePointUtil.getSharePointLookupPath(className);
		File newFile = new File(System.getProperty("java.io.tmpdir") + FileSeparator + file.getOriginalFilename());
		OutputStream os = new FileOutputStream(newFile);
		os.write(file.getBytes());
		os.close();
		sharePointUtil.readAndUploadFileToSharePoint(newFile, sharePointSourcePath);
	}

	// --------------get lkp data---------------------------------

	@Override
	public List<SpecLookUp> getSpecLkpData() {
		// TODO Auto-generated method stub
		return specLookUpRepository.findAll(Sort.by(Sort.Direction.ASC, "specCode"));
	}

	@Override
	public List<SubSpecDTO> getSubSpecLkpData() {
		// TODO Auto-generated method stub
		return lookUpDao.getSubSpecData();
	}

	@Override
	public List<MinMaxAgeLookUp> getMinMaxAgeData() {
		// TODO Auto-generated method stub
		return minMaxAgeRepository.findAll(Sort.by(Sort.Direction.ASC, "minMaxAgeDesc"));
	}

	@Override
	public List<RevenueCodeLookUp> getRevCodeData() {
		// TODO Auto-generated method stub
		return revenueCodeRepository.findAll(Sort.by(Sort.Direction.ASC, "revCode"));
	}

	@Override
	public List<BillTypeLookUp> getBillTypeData() {
		// TODO Auto-generated method stub
		return billTypeLookUpRepository.findAll(Sort.by(Sort.Direction.ASC, "billType"));
	};

	@Override
	public List<ConditionCodeLookUp> getCondCodeData() {
		// TODO Auto-generated method stub
		return conditionCodeRepository.findAll(Sort.by(Sort.Direction.ASC, "condCode"));
	}

	@Override
	public List<ModLookUp> getModLkpData() {
		// TODO Auto-generated method stub
		return lookUpDao.getModLkpData();
	}

	@Override
	public List<CCIDeviations> getCciDeviationsData() {
		// TODO Auto-generated method stub
		return cciDeviationRepository.getCciDeviationsData();
	}

	@Override
	public List<PosLookUp> getPosLkpData() {
		// TODO Auto-generated method stub
		return posLookUpRepository.findAll(Sort.by(Sort.Direction.ASC, "posCode"));
	}

	@Override
	public List<PolicyCategoryLookUp> getPolicyCategoryData() {
		// TODO Auto-generated method stub
		return policyCatLookUpRepository.findAll(Sort.by(Sort.Direction.ASC, "policyCategoryLkpId"));
	}

	@Override
	public List<ReasonCodeLookUp> getReasonCodeData() {
		// TODO Auto-generated method stub
		return reasonCodeLookUpRepository.findAll(Sort.by(Sort.Direction.ASC, "reasonCode"));
	}

	@Override
	public List<ModifierPriority> getModifierPriorityData() {
		// TODO Auto-generated method stub
		return modifierPriorityRepository.findAll(Sort.by(Sort.Direction.ASC, "modifier"));
	}

	@Override
	public List<ModifierInteraction> getModifierInteractionData() {
		// TODO Auto-generated method stub
		return modifierInteractionRepository.findAll(Sort.by(Sort.Direction.ASC, "miKey"));
	}

	@Override
	public List<ModifierInteractionType> getModifierInteractionTypeData() {
		// TODO Auto-generated method stub
		return modifierInteractionTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "mitKey"));
	}

	@Override
	public List<CCI> getCciLkpData() {
		// TODO Auto-generated method stub
		return CciRepository.findAll(Sort.by(Sort.Direction.ASC, "cciKey"));
	}

	@Override
	public List<CciRationaleLookup> getCciRationaleLkpData() {
		// TODO Auto-generated method stub
		return ptpCciRationaleRepository.findAll(Sort.by(Sort.Direction.ASC, "cciRationaleKey"));
	}

	@Override
	public List<ClaimTypeLinkLkp> getClaimTypeLinkLkpData() {
		// TODO Auto-generated method stub
		return claimTypeLinkLkpRepository.findAll(Sort.by(Sort.Direction.ASC, "claimTypeLinkLkpKey"));
	}

	@Override
	public List<MueRationalLookUp> getMueRationaleLkpData() {
		// TODO Auto-generated method stub
		return MueRationalLkpRepository.findAll(Sort.by(Sort.Direction.ASC, "mueRationalKey"));
	}

	@Override
	public List<BwTypeLookUp> getBwTypeLkpData() {
		// TODO Auto-generated method stub
		return BwTypeLkpRepository.findAll(Sort.by(Sort.Direction.ASC, "bwTypeKey"));
	}

	@Override
	public List<MaxUnitsTypes> getMaxUnitsTypesData() {
		// TODO Auto-generated method stub
		return MaxUnitsTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "maxUnitsTypeKey"));
	}

	@Override
	public List<BoTypeLookUp> getBoLkpData() {
		// TODO Auto-generated method stub
		return BoLkpRepository.findAll(Sort.by(Sort.Direction.ASC, "boKey"));
	}

	@Override
	public List<MaxUnitsLookUp> getMaxUnitsLkpData() {
		// TODO Auto-generated method stub
		return MaxUnitsLookUpRepository.findAll(Sort.by(Sort.Direction.ASC, "maxUnitsLkpKey"));
	}

	@Override
	public List<ModifierPayPercentage> getModifierPayPercentageData() {
		// TODO Auto-generated method stub
		return modifierPayPercentageRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	@Override
	public List<ModifierPayPercentageLkp> getModifierPayPercentageLkpData() {
		// TODO Auto-generated method stub
		return ModifierPayPercentageLkpRepository.findAll();
	}

	@Override
	public List<statesDTO> getStatesData() {
		String Query = "select * from states";
		List<statesDTO> resultSet = dragonNamedParameterJdbcTemplate.query(Query,
				new BeanPropertyRowMapper<>(statesDTO.class));
		return resultSet;
	}

	// ---------------------------post lkp data---------------------------------
	@Override
	public void saveSpecLkpData(SpecLookUp specLkp) {
		// TODO Auto-generated method stub
		specLookUpRepository.save(specLkp);
	}

	@Override
	public void savesubSpecLkpData(SubSpecMap subSpecMap) {
		// TODO Auto-generated method stub
		subSpecLookUpRepository.save(subSpecMap);

	}

	@Override
	public void saveMinMaxAgeLkpData(MinMaxAgeLookUp minMaxAgeLookUp) {
		// TODO Auto-generated method stub
		minMaxAgeLookUpRepository.save(minMaxAgeLookUp);
	}

	@Override
	public void saveRevCodeLkpData(RevenueCodeLookUp revenueCodeLookUp) {
		// TODO Auto-generated method stub
		revenueCodeLookUpRepository.save(revenueCodeLookUp);
	}

	@Override
	public void saveBillTypeLkpData(BillTypeLookUp billTypeLookUp) {
		// TODO Auto-generated method stub
		billTypeLookUpRepository.save(billTypeLookUp);
	}

	@Override
	public void saveCondCodeLkpData(ConditionCodeLookUp conditionCodeLookUp) {
		// TODO Auto-generated method stub
		conditionCodeRepository.save(conditionCodeLookUp);
	}

	@Override
	public void EditModLkpData(ModLookUp modLookUp) {
		// TODO Auto-generated method stub
		modLookUpRepository.updateModLkp(modLookUp.getCptMod(), modLookUp.getDescription(), modLookUp.getStartDate(),
				modLookUp.getEndDate(), modLookUp.getIsCci(), modLookUp.getIs_59_group(), modLookUp.getAmbulanceModB());
	}

	@Override
	public void saveModLkpData(ModLookUp modLookUp) {
		// TODO Auto-generated method stub
		modLookUpRepository.save(modLookUp);
	}

	@Override
	public void savePosLkpData(PosLookUp posLookUp) {
		// TODO Auto-generated method stub
		posLookUpRepository.save(posLookUp);
	}

	@Override
	public void savePolicyCategoryLkpData(PolicyCategoryLookUp policyCategoryLookUp) {
		// TODO Auto-generated method stub
		policyCatLookUpRepository.save(policyCategoryLookUp);
	}

	@Override
	public void saveReasonCodeLkp(ReasonCodeLookUp reasonCodeLookUp) {
		// TODO Auto-generated method stub
		reasonCodeLookUpRepository.save(reasonCodeLookUp);
	}

	@Override
	public void saveCciRationaleLkp(CciRationaleLookup cciRationaleLookUp) {
		// TODO Auto-generated method stub
		ptpCciRationaleRepository.save(cciRationaleLookUp);
	}

	@Override
	public void saveCciLkp(CCI cci) {
		// TODO Auto-generated method stub
		CciRepository.save(cci);
	}

	@Override
	public void saveMueRationaleLkp(MueRationalLookUp mueRationalLookUp) {
		// TODO Auto-generated method stub
		MueRationalLkpRepository.save(mueRationalLookUp);
	}

	@Override
	public void saveBwtypeLkp(BwTypeLookUp bwTypeLookUp) {
		// TODO Auto-generated method stub
		BwTypeLkpRepository.save(bwTypeLookUp);
	}

	@Override
	public void saveMaxUnitsTypes(MaxUnitsTypes maxUnitsTypes) {
		// TODO Auto-generated method stub
		MaxUnitsTypeRepository.save(maxUnitsTypes);
	}

	@Override
	public void saveBoLkp(BoTypeLookUp boTypeLookUp) {
		// TODO Auto-generated method stub
		BoLkpRepository.save(boTypeLookUp);
	}

	@Override
	public void saveMaxUnitsLkp(MaxUnitsLookUp maxUnitsLookUp) {
		// TODO Auto-generated method stub
		MaxUnitsLookUpRepository.save(maxUnitsLookUp);
	}

	@Override
	public void saveCciDeviationsData(CCIDeviationsDTO cciDeviationsDTO) {
		ModelMapper mapper = new ModelMapper();
		CCIDeviations cciDeviations;
		cciDeviations = mapper.map(cciDeviationsDTO, CCIDeviations.class);
		cciDeviations.setCreatedDate(new Date());
		cciDeviations.setUpdatedDate(new Date());
		CciDeviationRepository.save(cciDeviations);
		lookUpDao.updateDeviationsInCCI(cciDeviationsDTO.getCciKey(), cciDeviationsDTO.getColumnI(),
				cciDeviationsDTO.getColumnII());
	}

	public CCIDeviationsId getCCIDeviationsId(CCIDeviationsDTO cciDeviationsDTO) {
		CCIDeviationsId cciDeviationsId = new CCIDeviationsId();
		cciDeviationsId.setCciKey(cciDeviationsDTO.getCciKey());
		cciDeviationsId.setColumnI(cciDeviationsDTO.getColumnI());
		cciDeviationsId.setColumnII(cciDeviationsDTO.getColumnII());
		cciDeviationsId.setStartDate(cciDeviationsDTO.getStartDate());
		cciDeviationsId.setEndDate(cciDeviationsDTO.getEndDate());
		cciDeviationsId.setClientGroupId(cciDeviationsDTO.getClientGroupId());
		return cciDeviationsId;
	}

	@Override
	public void editCciDeviationsData(CCIDeviationsDTO cciDeviationsDTO) {
		disableOldRecord(cciDeviationsDTO.getDeviationsKey());
		cciDeviationsDTO.setDeviationsKey(null);
		saveCciDeviationsData(cciDeviationsDTO);
	}

	private void disableOldRecord(Integer deviationsKey) {
		String Query = "update source.cci_deviations set deleted_b = true where deviations_key=:deviationsKey";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("deviationsKey", deviationsKey);
		ipuNamedParameterJdbcTemplate.update(Query, namedParameters);
	}
}