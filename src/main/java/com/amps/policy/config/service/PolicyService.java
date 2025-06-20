package com.amps.policy.config.service;

import java.text.ParseException;
import java.util.List;
import java.sql.Date;

import com.amps.policy.config.dto.ChangesDTO;
import com.amps.policy.config.dto.PolicyConfigDTO;
import com.amps.policy.config.dto.ProceduresDTO;
import com.amps.policy.config.model.AddOnCodes;
import com.amps.policy.config.model.ChangeModifierLookUp;
import com.amps.policy.config.model.Changes;
import com.amps.policy.config.model.ClaimLinkLookUp;
import com.amps.policy.config.model.ClaimTypeLookUp;
import com.amps.policy.config.model.CptLinkLookUp;
import com.amps.policy.config.model.CptMaster;
import com.amps.policy.config.model.GenderLookUp;
import com.amps.policy.config.model.LobLookUp;
import com.amps.policy.config.model.MedicalPolicyLookup;
import com.amps.policy.config.model.MinMaxAgeLookUp;
import com.amps.policy.config.model.NpiLinkLookUp;
import com.amps.policy.config.model.Policy;
import com.amps.policy.config.model.PolicyActionCptLookUp;
import com.amps.policy.config.model.PosLinkLookUp;
import com.amps.policy.config.model.Procedures;
import com.amps.policy.config.model.ProductTypeLookUp;
import com.amps.policy.config.model.RevenueCodeClaimLink;
import com.amps.policy.config.model.SubPolicies;
import com.amps.policy.config.model.TaxLinkLookUp;
import com.amps.policy.config.model.TaxonomyLinkLookUp;

public interface PolicyService {

    /* --- Policy Core Methods --- */
    Integer savePolicy(PolicyConfigDTO policyConfigDTO);

    Policy getPolicyById(int id);

    Policy findByPolicyId(Integer policyId);

    void updatePolicy(Policy policy);

    List<Policy> getPolicyDetails();

    List<Policy> getPolicyNumber(int policyNumber, String version);

    boolean isPolicyNumberValid(int policyNumber);

    String findByPolicyVersion(int id);

    /* --- Save All Tabs Data --- */

    Integer saveTabsData(PolicyConfigDTO policyConfigDTO, Date createdDate,
                         String cloned, String clonedTaxonomy, Integer newPolicyId) throws ParseException;
    /* --- Claim Type Tab --- */

    List<ClaimTypeLookUp> getClaimTypes();

    List<SubPolicies> getSubPolicyDetails();

    /* --- Description Tab --- */
    List<LobLookUp> getLobDetails();

    /* --- Changes Tab --- */
    List<Changes> getChangesById(int id);

    Changes saveChanges(Changes changes);

    List<ChangesDTO> saveChangesListData(List<ChangesDTO> changes);

    List<Changes> getChangesByIdOpenB(int id);

    List<Changes> getChangesData();

    List<Changes> getAllChanges();

    List<ChangesDTO> getLatestChangesRecord(Integer policyId);


    /* --- Details Tab Lookups --- */

    List<GenderLookUp> getGenderDetails();

    List<ProductTypeLookUp> getProductTypeDetails();

    List<MedicalPolicyLookup> getMedicalPolicyDetails();

    List<NpiLinkLookUp> getNpiLinkDetails();

    List<PosLinkLookUp> getPosLinkDetails();

    List<TaxLinkLookUp> getTaxLinkDetails();

    List<TaxonomyLinkLookUp> getTaxonomyDetails();

    List<AddOnCodes> getAddOnCodes();

    List<RevenueCodeClaimLink> getRevenueCodeClaimLinks();

    List<CptLinkLookUp> getCptLinkDetails();

    List<ChangeModifierLookUp> getChangeModifiers();

    List<CptMaster> getCptData();

    List<ClaimLinkLookUp> getClmLinkLkpData();

    List<PolicyActionCptLookUp> getCptActionLkpData();
}

