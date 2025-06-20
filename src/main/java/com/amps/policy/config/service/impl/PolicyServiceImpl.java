package com.amps.policy.config.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.amps.policy.config.dao.ClientAssignmentDao;
import com.amps.policy.config.dto.ClientAssignmentDTO;
import com.amps.policy.config.dto.ProceduresDTO;
import com.amps.policy.config.model.*;
import com.amps.policy.config.repository.*;
import com.amps.policy.config.service.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

import com.amps.policy.config.dao.ProceduresDao;
import com.amps.policy.config.dto.ChangesDTO;
import com.amps.policy.config.dto.PolicyConfigDTO;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	PolicyRepository policyRepository;

	@Autowired
	ChangesRepository changesRepository;

	@Autowired
	ProceduresDao proceduresDao;

	@Autowired
	ProceduresRepository proceduresRepository;

	@Autowired
	GenderLookUpRepository genderLookUpRepository;

	@Autowired
	LobLookUpRepository lobLookUpRepository;

	@Autowired
	MedicalPoliciesRepository medicalPoliciesRepository;

	@Autowired
	MinMaxAgeLookUpRepository minMaxAgeRepository;

	@Autowired
	NpiLinkLookUpRepository npiLinkLookupRepository;

	@Autowired
	PosLinkLookUpRepository posLinkLookUpRepository;

	@Autowired
	AddOnCodesRepository addOnCodesRepository;

	@Autowired
	ProductTypeLookUpRepository productTypeLookupRepository;

	@Autowired
	SubPoliciesRepository subPoliciesRepository;

	@Autowired
	TaxLinkLookUpRepository taxLinkRepository;

	@Autowired
	TaxonomyLookUpRepository taxonomyLookUpRepository;

	@Autowired
	ClaimTypeLookUpRepository claimTypeLookUpRepository;

	@Autowired
	NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

	@Autowired
	RevenueCodeClaimLinkRepository revenueCodeClaimLinkRepository;

	@Autowired
	CptLinkRepository cptLinkRepository;

	@Autowired
	ChangeModifierRepository changeModifierRepository;

	@Autowired
	ClaimLinkLkpRepository claimLinkLkpRepository;

	@Autowired
	PolicyActionCptLookUpRepository policyActionCptLookUpRepository;

	@Autowired
	CptMasterRepository cptMasterRepository;

	private Integer policyId;

	@Autowired
	DiagnosisService diagnosisService;

	@Autowired
	PolicyBillTypeService policyBillTypeService;

	@Autowired
	PolicyConditionCodeService policyConditionCodeService;

	@Autowired
	ClientAssignmentDao clientAssignmentDao;

	@Autowired
	ClientAssignmentRepository clientAssignmentRepo;

	@Autowired
	TaxonomyService TaxonomyService;


	@Override
	public Integer savePolicy(PolicyConfigDTO policyConfigDTO) {

		Policy policy;
		ModelMapper modelMapper = new ModelMapper();

		policy = modelMapper.map(policyConfigDTO, Policy.class);

		if (policyConfigDTO.getPolicyNumber() == null) {
			int seqNumber = policyRepository.getSeqValue();
			policy.setPolicyNumber(seqNumber);
		}

		MedicalPolicyLookup medicalPolicies = new MedicalPolicyLookup();
		medicalPolicies.setMedicalPolicyKey(policyConfigDTO.getMedicalPolicyKey());
		policy.setMedicalPolicyKeyFK(medicalPolicies);

		SubPolicies subPolicies = new SubPolicies();
		subPolicies.setSubPolicyKey(policyConfigDTO.getSubPolicyKey());
		policy.setSubPolicyKeyFK(subPolicies);

		PolicyCategoryLookUp policyCategoryLookUp = new PolicyCategoryLookUp();
		policyCategoryLookUp.setPolicyCategoryLkpId(policyConfigDTO.getPolicyCategoryLkpId());
		policy.setPolicyCategoryLkpIdFk(policyCategoryLookUp);

		ReasonCodeLookUp reasonCodeLookUp = new ReasonCodeLookUp();
		reasonCodeLookUp.setReasonCode(policyConfigDTO.getReasonCodeFk());
		policy.setReasonCodeFk(reasonCodeLookUp);

		GenderLookUp genderLookUp = new GenderLookUp();
		genderLookUp.setGenderCode(policyConfigDTO.getGenderCode());
		policy.setGenderCode(genderLookUp);

		MinMaxAgeLookUp minMaxAgeLookUp = new MinMaxAgeLookUp();
		minMaxAgeLookUp.setMinMaxAgeLkpId(policyConfigDTO.getMinAgeFk());
		policy.setMinAgeFk(minMaxAgeLookUp);

		MinMaxAgeLookUp maxMixAgeLookUp = new MinMaxAgeLookUp();
		maxMixAgeLookUp.setMinMaxAgeLkpId(policyConfigDTO.getMaxAgeFk());
		policy.setMaxAgeFk(maxMixAgeLookUp);

		NpiLinkLookUp npiLinkLookUp = new NpiLinkLookUp();
		npiLinkLookUp.setNpiLinkLkpKey(policyConfigDTO.getNpiLogicFk());
		policy.setNpiLogicFk(npiLinkLookUp);

		ClaimTypeLinkLkp claimTypeLinkLkp = new ClaimTypeLinkLkp();
		claimTypeLinkLkp.setClaimTypeLinkLkpKey(policyConfigDTO.getClaimTypeLinkFk());
		policy.setClaimTypeLinkFk(claimTypeLinkLkp);

		PosLinkLookUp posLinkLookUp = new PosLinkLookUp();
		posLinkLookUp.setPosLinkLkpKey(policyConfigDTO.getPosLinkFk());
		policy.setPosLinkFk(posLinkLookUp);

		RevenueCodeClaimLink revenueCodeClaimLink = new RevenueCodeClaimLink();
		revenueCodeClaimLink.setRevenueCodeClaimLinkKey(policyConfigDTO.getRevenueCodeClaimLinkFk());
		policy.setRevenueCodeClaimLinkFk(revenueCodeClaimLink);

		TaxLinkLookUp taxLinkLookUp = new TaxLinkLookUp();
		taxLinkLookUp.setTaxLinkLkpKey(policyConfigDTO.getTaxLogicFk());
		policy.setTaxLogicFk(taxLinkLookUp);

		TaxonomyLinkLookUp taxonomyLinkLookUp = new TaxonomyLinkLookUp();
		taxonomyLinkLookUp.setTaxonomyLinkLkpKey(policyConfigDTO.getTaxonomyLogicFk());
		policy.setTaxonomyLogicFk(taxonomyLinkLookUp);

		LobLookUp lobLookUp = new LobLookUp();
		lobLookUp.setLobKey(policyConfigDTO.getLobFk());
		policy.setLobFk(lobLookUp);

		CptLinkLookUp cptLinkLookUp = new CptLinkLookUp();
		cptLinkLookUp.setCptLinkLkpKey(policyConfigDTO.getCptLinkFk());
		policy.setCptLinkFk(cptLinkLookUp);

//		ProductTypeLookUp productTypeLookUp = new ProductTypeLookUp();
//		String[] productType = policyConfigDTO.getProductTypeFk().split(",");
//		for(String data: productType){
//			productTypeLookUp.setProductKey(data);
		policy.setProductTypeFk(policyConfigDTO.getProductTypeFk());
//		}

		PolicyBillTypeLinkLookUp PolicyBillTypeLinkLookUp = new PolicyBillTypeLinkLookUp();
		PolicyBillTypeLinkLookUp.setBillTypeLinkKey(policyConfigDTO.getBillTypeLinkFk());
		policy.setBillTypeLinkFk(PolicyBillTypeLinkLookUp);

		PolicyActionBillTypeLookUp policyActionBillTypeLookUp = new PolicyActionBillTypeLookUp();
		policyActionBillTypeLookUp.setPolicyBillTypeActionKey(policyConfigDTO.getBillTypeActionFk());
		policy.setBillTypeActionFk(policyActionBillTypeLookUp);

		ConditionCodeActionLkp policyActionConditionLkp = new ConditionCodeActionLkp();
		policyActionConditionLkp.setCondCodeKey(policyConfigDTO.getConditionCodeActionFk());
		policy.setConditionCodeActionFk(policyActionConditionLkp);

		policy.setUpdatedBy("AMPS");
		policy.setCreatedBy("AMPS");
		Date updateDate = new Date(System.currentTimeMillis());
		policy.setUpdatedOn(updateDate);
		var now = Instant.now();
		policy.setUpdatedOn(Date.from(now));
		if (policy.getCreatedDate() == null) {
			policy.setCreatedDate(Date.from(now));
		}

		policyRepository.save(policy);

		return policy.getPolicyId();

	}

	@Override
	public Policy getPolicyById(int id) {
		return policyRepository.getBypolicyData(id);
	}


	@Override
	public Policy findByPolicyId(Integer policyId) {
		return policyRepository.findByPolicyId(policyId);
	}

	@Override
	public void updatePolicy(Policy policy) {
		policyRepository.save(policy);
	}

	@Override
	public List<Policy> getPolicyDetails() {
		return policyRepository.findAll(Sort.by(Sort.Direction.ASC, "description"));
	}

	@Override
	public List<Policy> getPolicyNumber(int policyNumber, String version) {
		return policyRepository.findByPolicynumber(policyNumber, version);
	}

	@Override
	public boolean isPolicyNumberValid(int policyNumber) {
		return policyRepository.findByPolicyNumber(policyNumber) != null;
	}

	@Override
	public String findByPolicyVersion(int id) {
		return policyRepository.findByPolicyVersion(id);
	}


	@Override
	public List<ClaimTypeLookUp> getClaimTypes() {
		return claimTypeLookUpRepository.findAll(Sort.by(Sort.Direction.ASC, "claimType"));
	}

	@Override
	public List<SubPolicies> getSubPolicyDetails() {
		return subPoliciesRepository.findAll(Sort.by(Sort.Direction.ASC, "subPolicyTitle"));
	}

	@Override
	public List<LobLookUp> getLobDetails() {
		return lobLookUpRepository.findAll();
	}

	@Override
	public List<Changes> getChangesById(int id) {
		return changesRepository.findChangesById(id);
	}

	@Override
	public Changes saveChanges(Changes changes) {
		return changesRepository.save(changes);
	}
	@Override
	public List<Changes> getAllChanges() {
		return List.of();
	}

	@Override
	public List<ChangesDTO> getLatestChangesRecord(Integer policyId) {
		String Query = "select policy_id_fk as policyId,is_open_b as isOpenb,* from policy.policy_changes where policy_id_fk =:policyId order by updated_on desc limit 1";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		return ipuNamedParameterJdbcTemplate.query(Query, namedParameters,
				new BeanPropertyRowMapper<>(ChangesDTO.class));
	}



	public List<ProceduresDTO> getProcsExportById(int id) {
		return proceduresDao.findByPolicyId(id);
	}

	@Override
	public List<ChangesDTO> saveChangesListData(List<ChangesDTO> changes) {
		var now = Instant.now();
		for (ChangesDTO change : changes) {
			if (change.getUpdatedOn() == null) {
				change.setUpdatedOn(Date.from(now));
			}
		}
		String query = "INSERT INTO policy.policy_changes(policy_id_fk, jira_id, jira_desc, jira_link, userid, updated_on, is_open_b) VALUES (:policyId, :jiraId, :jiraDesc, :jiraLink, :userId, :updatedOn, :isOpenb)";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(changes.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(query, params);
		return changes;
	}

	@Override
	public List<Changes> getChangesData() {
		return changesRepository.findAll();
	}


	@Override
	public List<GenderLookUp> getGenderDetails() {
		return genderLookUpRepository.findAll(Sort.by(Sort.Direction.ASC, "genderDesc"));
	}

	@Override
	public List<ProductTypeLookUp> getProductTypeDetails() {
		return productTypeLookupRepository.findAll(Sort.by(Sort.Direction.ASC, "productTitle"));
	}

	@Override
	public List<Changes> getChangesByIdOpenB(int id) {

		return changesRepository.GetOpenBChangesDataById(id);
	}

	@Override
	public List<MedicalPolicyLookup> getMedicalPolicyDetails() {
		return medicalPoliciesRepository.findAll(Sort.by(Sort.Direction.ASC, "medicalPolicyTitle"));
	}


	@Override
	public List<NpiLinkLookUp> getNpiLinkDetails() {
		return npiLinkLookupRepository.findAll(Sort.by(Sort.Direction.ASC, "npiLinkLkpKey"));
	}

	@Override
	public List<PosLinkLookUp> getPosLinkDetails() {
		return posLinkLookUpRepository.findAll(Sort.by(Sort.Direction.ASC, "posLinkLkpKey"));
	}

	@Override
	public List<TaxLinkLookUp> getTaxLinkDetails() {
		return taxLinkRepository.findAll(Sort.by(Sort.Direction.ASC, "taxLinkLkpKey"));
	}

	@Override
	public List<TaxonomyLinkLookUp> getTaxonomyDetails() {
		return taxonomyLookUpRepository.findAll(Sort.by(Sort.Direction.ASC, "taxonomyLinkLkpKey"));
	}

	@Override
	public List<AddOnCodes> getAddOnCodes() {
		return addOnCodesRepository.findAll(Sort.by(Sort.Direction.ASC, "boKey"));
	}

	@Override
	public List<RevenueCodeClaimLink> getRevenueCodeClaimLinks() {
		return revenueCodeClaimLinkRepository.findAll(Sort.by(Sort.Direction.ASC, "revenueCodeClaimLinkKey"));
	}

	@Override
	public List<CptLinkLookUp> getCptLinkDetails() {
		return cptLinkRepository.findAll(Sort.by(Sort.Direction.ASC, "cptLinkLkpKey"));
	}

	@Override
	public List<ChangeModifierLookUp> getChangeModifiers() {
		return changeModifierRepository.findAll(Sort.by(Sort.Direction.ASC, "changeModifierKey"));
	}

	@Override
	public List<CptMaster> getCptData() {
		return cptMasterRepository.findAll();
	}

	@Override
	public List<ClaimLinkLookUp> getClmLinkLkpData() {
		return claimLinkLkpRepository.findAll();
	}

	@Override
	public List<PolicyActionCptLookUp> getCptActionLkpData() {
		return policyActionCptLookUpRepository.findAll();
	}

	@Override
	public Integer saveTabsData(PolicyConfigDTO policyConfigDTO, Date createdDate, String cloned, String clonedTaxonomy, Integer newPolicyId) throws ParseException {
		if (policyConfigDTO.getCustom() == 1) {
			Integer latestVersion = policyRepository.getLatestVersion(policyConfigDTO.getPolicyNumber());
			policyConfigDTO.setPolicyVersion(latestVersion + 1);
		}
		this.policyId = savePolicy(policyConfigDTO);

		if (policyConfigDTO.getClonedPolicyId() == null) {
			policyConfigDTO.setClonedPolicyId(newPolicyId);
		}
		int clonedPolicyId = policyConfigDTO.getClonedPolicyId();
		if (policyConfigDTO.getClonedPolicyId() != null) {
			copyDiagnosisData(clonedPolicyId, policyId);
			copyPolicyBillTypes(clonedPolicyId, policyId);
			copyPolicyConditions(clonedPolicyId, policyId);
			copyProcedures(clonedPolicyId, policyId);

			if ("true".equals(cloned) && policyConfigDTO.getForClientTabPolicyId() == null) {
				mapClientAssignmentData(policyConfigDTO.getClonedPolicyId(), createdDate);
			}
			if ("true".equals(clonedTaxonomy)) {
				copyTaxonomy(policyConfigDTO.getTaxonomyList(), policyId);
			}
		}

		if (policyConfigDTO.getForClientTabPolicyId() != null) {
			mapClientAssignmentData(policyConfigDTO.getForClientTabPolicyId(), createdDate);
		}

		if (policyConfigDTO.isAddAllActiveClients()) {
			List<ClientAssignmentDTO> clientAssignmentDTO = clientAssignmentDao.getActiveClientGroupsNotHp();
			SaveClientAssignmentData(clientAssignmentDTO, createdDate);
		}
		return policyId;
	}

	public void mapClientAssignmentData(Integer policyId, java.sql.Date createdDate) throws ParseException {
		List<ClientAssignment> clientAssignmentData = null;

		clientAssignmentData = clientAssignmentRepo.findByPolicyId(policyId);
		ModelMapper mapper = new ModelMapper();
		List<ClientAssignmentDTO> clientAssignmentDto;
		clientAssignmentDto = clientAssignmentData.stream()
				.map(clientAssignment -> mapper.map(clientAssignment, ClientAssignmentDTO.class)).toList();
		SaveClientAssignmentData(clientAssignmentDto, createdDate);
	}

	public void SaveClientAssignmentData(List<ClientAssignmentDTO> clientAssignmentList, java.sql.Date createdDate)
			throws ParseException {
		String endDate = "9999-12-31";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = dateFormat.parse(endDate);
		java.sql.Date defaultEndDate = new java.sql.Date(utilDate.getTime());

		if (!clientAssignmentList.isEmpty()) {
			clientAssignmentList.forEach(clientAssignment -> {
				clientAssignment.setPolicyId(policyId);
				clientAssignment.setClientStartDate(createdDate);
				if (clientAssignment.getClientEndDate() == null) {
					clientAssignment.setClientEndDate(defaultEndDate);
				}
			});
			clientAssignmentDao.postClientAssignmentData1(clientAssignmentList);
		}

	}

	private void copyDiagnosisData(int clonedPolicyId, int policyId) {
		List<Diagnosis> diagnosisData = diagnosisService.getDiagnosisData(clonedPolicyId);
		if (!diagnosisData.isEmpty()) {
			diagnosisService.saveDiagnosisListData(
					diagnosisData.stream()
							.peek(d -> d.setPolicyId(policyId))
							.collect(Collectors.toList())
			);
		}
	}

	private void copyPolicyBillTypes(int clonedPolicyId, int policyId) {
		List<PolicyBillType> billTypes = policyBillTypeService.getPolicyBillTypeDataById(clonedPolicyId);
		if (!billTypes.isEmpty()) {
			policyBillTypeService.savePolicyBillTypeData(
					billTypes.stream()
							.peek(bt -> bt.setPolicyId(policyId))
							.collect(Collectors.toList())
			);
		}
	}

	private void copyPolicyConditions(int clonedPolicyId, int policyId) {
		List<PolicyConditionCode> conditions = policyConditionCodeService.getPolicyConditionDataById(clonedPolicyId);
		if (!conditions.isEmpty()) {
			policyConditionCodeService.postConditionTypeData(
					conditions.stream()
							.peek(c -> c.setPolicyId(policyId))
							.collect(Collectors.toList())
			);
		}
	}

	private void copyTaxonomy(List<Taxonomy> taxonomyList, int policyId) {
		if (taxonomyList != null && !taxonomyList.isEmpty()) {
			TaxonomyService.postTaxonomyData(
					taxonomyList.stream()
							.peek(t -> t.setPolicyId(policyId))
							.collect(Collectors.toList())
			);
		}
	}

	private void copyProcedures(int clonedPolicyId, int policyId) {
		List<ProceduresDTO> procedureData = proceduresDao.findByProceduresData(clonedPolicyId);
		if (procedureData != null && !procedureData.isEmpty()) {
			proceduresDao.PostProceduresData(
					procedureData.stream()
							.peek(p -> p.setPolicyId(policyId))
							.collect(Collectors.toList())
			);
		}
	}
}