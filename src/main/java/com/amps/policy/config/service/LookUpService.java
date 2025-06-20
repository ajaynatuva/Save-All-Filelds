package com.amps.policy.config.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.amps.policy.config.dto.SubSpecDTO;
import com.amps.policy.config.dto.statesDTO;
import com.amps.policy.config.model.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

public interface LookUpService {

	void readFileAndSaveFile(String className, MultipartFile file);

	void uploadToSharePoint(String className, MultipartFile file) throws FileNotFoundException, IOException;

	// getting lkp data
	List<SpecLookUp> getSpecLkpData();

	List<SubSpecDTO> getSubSpecLkpData();

	List<MinMaxAgeLookUp> getMinMaxAgeData();

	List<RevenueCodeLookUp> getRevCodeData();

	List<BillTypeLookUp> getBillTypeData();

	List<ConditionCodeLookUp> getCondCodeData();

	List<ModLookUp> getModLkpData();

	List<PosLookUp> getPosLkpData();

	List<PolicyCategoryLookUp> getPolicyCategoryData();

	List<ReasonCodeLookUp> getReasonCodeData();

	List<ModifierPriority> getModifierPriorityData();

	List<ModifierInteraction> getModifierInteractionData();

	List<ModifierInteractionType> getModifierInteractionTypeData();

	List<CCI> getCciLkpData();

	List<CciRationaleLookup> getCciRationaleLkpData();

	List<ClaimTypeLinkLkp> getClaimTypeLinkLkpData();

	List<MueRationalLookUp> getMueRationaleLkpData();

	List<BwTypeLookUp> getBwTypeLkpData();

	List<MaxUnitsTypes> getMaxUnitsTypesData();

	List<BoTypeLookUp> getBoLkpData();

	List<MaxUnitsLookUp> getMaxUnitsLkpData();

	List<ModifierPayPercentage> getModifierPayPercentageData();

	List<ModifierPayPercentageLkp> getModifierPayPercentageLkpData();

	List<CCIDeviations> getCciDeviationsData();
	
	List<statesDTO> getStatesData();



	// saving lkp data

	public void saveSpecLkpData(SpecLookUp specLkp);

	public void savesubSpecLkpData(SubSpecMap subSpecMap);

	public void saveMinMaxAgeLkpData(MinMaxAgeLookUp minMaxAgeLookUp);

	public void saveRevCodeLkpData(RevenueCodeLookUp revenueCodeLookUp);

	public void saveBillTypeLkpData(BillTypeLookUp billTypeLookUp);

	public void saveCondCodeLkpData(ConditionCodeLookUp conditionCodeLookUp);

	public void EditModLkpData(ModLookUp modLookUp);

	public void saveModLkpData(ModLookUp modLookUp);

	public void savePosLkpData(PosLookUp posLookUp);

	public void savePolicyCategoryLkpData(PolicyCategoryLookUp policyCategoryLookUp);

	public void saveReasonCodeLkp(ReasonCodeLookUp reasonCodeLookUp);

	public void saveCciRationaleLkp(CciRationaleLookup cciRationaleLookUp);

	public void saveCciLkp(CCI cci);

	public void saveMueRationaleLkp(MueRationalLookUp mueRationalLookUp);

	public void saveBwtypeLkp(BwTypeLookUp bwTypeLookUp);

	public void saveMaxUnitsTypes(MaxUnitsTypes maxUnitsTypes);

	public void saveBoLkp(BoTypeLookUp boTypeLookUp);

	public void saveMaxUnitsLkp(MaxUnitsLookUp maxUnitsLookUp);

	public void saveCciDeviationsData(CCIDeviationsDTO cciDeviationsDTO);

	public void editCciDeviationsData(CCIDeviationsDTO cciDeviationsDTO);
}
