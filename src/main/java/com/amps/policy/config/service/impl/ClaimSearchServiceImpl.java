package com.amps.policy.config.service.impl;

import com.amps.policy.config.dto.ClaimSearchDTO;
import com.amps.policy.config.model.MedicalPolicyLookup;
import com.amps.policy.config.repository.MedicalPoliciesRepository;
import com.amps.policy.config.service.ClaimSearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ClaimSearchServiceImpl implements ClaimSearchService {

	Logger logger = LogManager.getLogger(ClaimSearchServiceImpl.class.getName());

	@Autowired
	NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

	ModelMapper modelMapper = new ModelMapper();

	public String claimConditionQuery(ClaimSearchDTO claimSearchDto) {
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		String query = "";

		if (claimSearchDto.getPolicyNumber() != null && String.valueOf(claimSearchDto.getPolicyNumber()) != "") {
			String policyNum = claimSearchDto.getPolicyNumber();
			String[] policyNumberAndVersionArray = policyNum.replaceAll(" ", "").split(",");
			query += " AND (";
			for (int i = 0; i < policyNumberAndVersionArray.length; i++) {
				String[] polVerArray = policyNumberAndVersionArray[i].split("\\.");
				query += "p.policy_number::text like " + "'%" + polVerArray[0] + "%' ";
				if (polVerArray.length > 1) {
					query += "AND p.policy_version::text like " + "'%" + polVerArray[1] + "%'";
				} else {
					query += "AND p.policy_version::text like " + "'%" + 0 + "%'";
				}
				if (!(i == policyNumberAndVersionArray.length - 1)) {
					query += " or ";
				}
			}
			query += ")";
		}
		if (claimSearchDto.getPolicyNumberF() != null && claimSearchDto.getPolicyNumberF() != "") {
			String[] polVerArray = claimSearchDto.getPolicyNumberF().split("\\.");
			query += "AND (";
			query += " p.policy_number::text like" + "'%" + polVerArray[0] + "%'";
			if (polVerArray.length > 1) {
				query += "AND p.policy_version::text like " + "'%" + polVerArray[1] + "%'";
			}
			query += " )";
		}

		if (claimSearchDto.getReasonCode() != null && claimSearchDto.getReasonCode() != "") {
			query += " AND ir.reason_code=" + "'" + claimSearchDto.getReasonCode() + "'";
		}

		if (claimSearchDto.getDrgnClaimId() != null && claimSearchDto.getDrgnClaimId() != "") {
			String drgnClaimId = claimSearchDto.getDrgnClaimId().replaceAll(" ", "");
			String[] drgnClaimIdArray = drgnClaimId.split(",");
			query += " AND (";
			for (int i = 0; i < drgnClaimIdArray.length; i++) {
				query += "icl.drg_claim_id::text like " + "'%" + drgnClaimIdArray[i] + "%'";
				if (!(i == drgnClaimIdArray.length - 1)) {
					query += " or ";
				}
			}
			query += ")";
		}

		if (claimSearchDto.getClaimSlId() != null) {
			query += " AND icl.drg_claim_sl_id::text like" + "'%" + claimSearchDto.getClaimSlId() + "%'";
		}
		if (claimSearchDto.getItemizedBillLineId()!= null && claimSearchDto.getItemizedBillLineId() != "") {
			query += " AND icl.itemized_bill_line_id::text like" + "'%" + claimSearchDto.getItemizedBillLineId() + "%'";
		}
		if (claimSearchDto.getClaimType() != null && claimSearchDto.getClaimType() != "") {
			String claimData = claimSearchDto.getClaimType();
			String[] clmTypes = claimData.split(",");
			String clmTypeAppened = "%";
			for (String clm : clmTypes) {
				clmTypeAppened += clm + "%";
			}
			query += " AND p.claim_type like" + "'" + clmTypeAppened + "'";
		}
		if (claimSearchDto.getMedicalPolicyKeyFk() != null) {
			query += " AND p.medical_policy_key_fk=" + claimSearchDto.getMedicalPolicyKeyFk();
		}
		if (claimSearchDto.getPosOrBillType() != null && claimSearchDto.getPosOrBillType() != "") {
			query += " AND ic.pos_or_bill_type::text like" + "'%" + claimSearchDto.getPosOrBillType() + "%'";
		}
		if (claimSearchDto.getPosOrBillTypeF() != null && claimSearchDto.getPosOrBillTypeF() != "") {
			query += " AND ic.pos_or_bill_type::text like" + "'%" + claimSearchDto.getPosOrBillTypeF() + "%'";
		}
		if (claimSearchDto.getProcessedFrom() != null || claimSearchDto.getProcessedTo() != null) {
			query += " AND icl.created_date between ";
			if (claimSearchDto.getProcessedFrom() != null) {
				LocalDateTime localDateTimeForProcessedFrom = LocalDateTime.parse(claimSearchDto.getProcessedFrom(),
						formatter1);
				query += "'" + localDateTimeForProcessedFrom + "'";
			} else {
				query += "'2000-01-01'";
			}
			query += " AND ";
			if (claimSearchDto.getProcessedTo() != null) {
				LocalDateTime localDateTimeForProcessedTo = LocalDateTime.parse(claimSearchDto.getProcessedTo(),
						formatter1);

				query += "'" + localDateTimeForProcessedTo + "'";
			} else {
				query += "'9999-12-31'";
			}
		}
		if (claimSearchDto.getClientgroupTypeCode() != null && claimSearchDto.getClientgroupTypeCode() != "") {
			query += " AND ic.client_group_type_code::text like" + "'%" + claimSearchDto.getClientgroupTypeCode() + "%'";
		}

		if (claimSearchDto.getClientGroup() != null && claimSearchDto.getClientGroup() != "") {
			query += " AND ic.client_group::text like" + "'%" + claimSearchDto.getClientGroup() + "%'";
		}

		if (claimSearchDto.getClmFormType() != null && claimSearchDto.getClmFormType() != "") {
			query += " AND ic.clm_form_type::text like" + "'%" + claimSearchDto.getClmFormType() + "%'";
		}

		if (claimSearchDto.getDrgnClaimIdF() != null && claimSearchDto.getDrgnClaimIdF() != "") {
			String drgnClaimIdF = "'" + claimSearchDto.getDrgnClaimIdF().replace(",", "', '") + "'";
			query += " AND icl.drg_claim_id::text In" +"("+drgnClaimIdF+")";

		}
		if (claimSearchDto.getIpuClaimLineId() != null && claimSearchDto.getIpuClaimLineId() != "") {
			String ClaimSlId = "'" + claimSearchDto.getIpuClaimLineId().replace(",", "', '") + "'";
			query += " AND icl.drg_claim_sl_id::text In" +"("+ClaimSlId+")";
		}

		if (claimSearchDto.getSubmittedProcedureCode() != null && claimSearchDto.getSubmittedProcedureCode() != "") {
			query += " AND icl.submitted_procedure_code::text ilike" + "'%" + claimSearchDto.getSubmittedProcedureCode() + "%'";

		}
		if (claimSearchDto.getIpuClmType() != null && claimSearchDto.getIpuClmType() != "") {
			query += " AND ic.ipu_clm_type::text like" + "'%" + claimSearchDto.getIpuClmType() + "%'";

		}
		if (claimSearchDto.getSubmitted_modifier_1() != null && claimSearchDto.getSubmitted_modifier_1() != "") {
			query += " AND icl.submitted_modifier_1::text ilike" + "'%" + claimSearchDto.getSubmitted_modifier_1() + "%'";

		}
		if (claimSearchDto.getSubmitted_modifier_2() != null && claimSearchDto.getSubmitted_modifier_2() != "") {
			query += " AND icl.submitted_modifier_2::text ilike" + "'%" + claimSearchDto.getSubmitted_modifier_2() + "%'";

		}
		if (claimSearchDto.getSubmitted_modifier_3() != null && claimSearchDto.getSubmitted_modifier_3() != "") {
			query += " AND icl.submitted_modifier_3::text ilike" + "'%" + claimSearchDto.getSubmitted_modifier_3() + "%'";

		}
		if (claimSearchDto.getSubmitted_modifier_4() != null && claimSearchDto.getSubmitted_modifier_4() != "") {
			query += " AND icl.submitted_modifier_4::text ilike" + "'%" + claimSearchDto.getSubmitted_modifier_4() + "%'";

		}
		if (claimSearchDto.getDosFrom() != null && claimSearchDto.getDosFrom() != "") {
			query += " AND icl.dos_from::text ilike" + "'%" + claimSearchDto.getDosFrom() + "%'";

		}
		if (claimSearchDto.getDosTo() != null && claimSearchDto.getDosTo() != "") {
			query += " AND icl.dos_to::text ilike" + "'%" + claimSearchDto.getDosTo() + "%'";

		}
		if (claimSearchDto.getSubmittedChargeAmount() != null && claimSearchDto.getSubmittedChargeAmount() != "") {
			query += " AND icl.submitted_charge_amount::text ilike" + "'%" + claimSearchDto.getSubmittedChargeAmount() + "%'";

		}
		if (claimSearchDto.getDrgnChallengeCode() != null && claimSearchDto.getDrgnChallengeCode() != "") {
			query += " AND icl.drgn_challenge_code::text ilike" + "'%" + claimSearchDto.getDrgnChallengeCode() + "%'";

		}
		if (claimSearchDto.getIpuChallengeCode() != null && claimSearchDto.getIpuChallengeCode() != "") {
			query += " AND ir.challenge_code::text ilike" + "'%" + claimSearchDto.getIpuChallengeCode() + "%'";

		}
		if (claimSearchDto.getDrgnChallengeAmt() != null && claimSearchDto.getDrgnChallengeAmt() != "") {
			query += " AND icl.drgn_challenge_amt::text ilike" + "'%" + claimSearchDto.getDrgnChallengeAmt() + "%'";

		}
		if (claimSearchDto.getIpuChallengeAmt() != null && claimSearchDto.getIpuChallengeAmt() != "") {
			query += " AND ir.amount::text ilike" + "'%" + claimSearchDto.getIpuChallengeAmt() + "%'";
		}

		if (claimSearchDto.getIpuClaimRunStatusId() != null && claimSearchDto.getIpuClaimRunStatusId() != "") {
			query += " AND ic.ipu_claim_run_status_id::text ilike" + "'%" + claimSearchDto.getIpuClaimRunStatusId() + "%'";
		}

		if (claimSearchDto.getRefDrgnClaimSlId() != null && claimSearchDto.getRefDrgnClaimSlId() != "") {
			query += " AND irl.drgn_claim_sl_id::text ilike" + "'%" + claimSearchDto.getRefDrgnClaimSlId() + "%'";
		}
		if (claimSearchDto.getConditionCodes() != null && claimSearchDto.getConditionCodes() != "") {
			query += " AND ic.cond_code::text ilike" + "'%" + claimSearchDto.getConditionCodes() + "%'";
		}
		if (claimSearchDto.getPatientId() != null) {
			query += " AND ic.ipu_patient_id::text ilike" + "'%" + claimSearchDto.getPatientId() + "%'";
		}
		if (claimSearchDto.getReasonCodeF() != null && claimSearchDto.getReasonCodeF() != "") {
			query += " AND ir.reason_code::text ilike" + "'%" + claimSearchDto.getReasonCodeF() + "%'";
		}
		if (claimSearchDto.getPrincipalDiagsF() != null && claimSearchDto.getPrincipalDiagsF() != "") {
			query += " AND ic.principal_diags::text ilike" + "'%" + claimSearchDto.getPrincipalDiagsF() + "%'";
		}
		if (claimSearchDto.getrefDrgnClaimIdF() != null && claimSearchDto.getrefDrgnClaimIdF() != "") {
			query += " AND irl.drgn_claim_id::text ilike" + "'%" + claimSearchDto.getrefDrgnClaimIdF() + "%'";
		}

		if (claimSearchDto.getAllowedQuantity() != null) {
			query += " AND icl.allowed_quantity::text ilike" + "'%" + claimSearchDto.getAllowedQuantity() + "%'";
		}

		if (claimSearchDto.getPayerAllowedModifier1() != null && claimSearchDto.getPayerAllowedModifier1() !="") {
			query += " AND icl.payer_allowed_modifier_1::text ilike" + "'%" + claimSearchDto.getPayerAllowedModifier1() + "%'";
		}
		if (claimSearchDto.getPayerAllowedModifier2() != null && claimSearchDto.getPayerAllowedModifier2() != "") {
			query += " AND icl.payer_allowed_modifier_2::text ilike" + "'%" + claimSearchDto.getPayerAllowedModifier2() + "%'";
		}
		if (claimSearchDto.getPayerAllowedModifier3() != null && claimSearchDto.getPayerAllowedModifier3() != "") {
			query += " AND icl.payer_allowed_modifier_3::text ilike" + "'%" + claimSearchDto.getPayerAllowedModifier3() + "%'";
		}
		if (claimSearchDto.getPayerAllowedModifier4() != null && claimSearchDto.getPayerAllowedModifier4() != "") {
			query += " AND icl.payer_allowed_modifier_4::text ilike" + "'%" + claimSearchDto.getPayerAllowedModifier4() + "%'";
		}
		if (claimSearchDto.getPayerAllowedUnits() != null && claimSearchDto.getPayerAllowedUnits() !="") {
			query += " AND icl.payer_allowed_units::text ilike" + "'%" + claimSearchDto.getPayerAllowedUnits() + "%'";
		}
		if (claimSearchDto.getPayerAllowedAmount() != null && claimSearchDto.getPayerAllowedAmount() !="") {
			query += " AND icl.payer_allowed_amount::text ilike" + "'%" + claimSearchDto.getPayerAllowedAmount() + "%'";
		}
		if (claimSearchDto.getPlaceOfService() != null && claimSearchDto.getPlaceOfService() !="") {
			query += " AND icl.pos::text ilike" + "'%" + claimSearchDto.getPlaceOfService() + "%'";
		}

		if (claimSearchDto.getRenderingProviderNpiLineLevel() != null && claimSearchDto.getRenderingProviderNpiLineLevel() !="") {
			query += " AND icl.rendering_provider_npi::text ilike" + "'%" + claimSearchDto.getRenderingProviderNpiLineLevel() + "%'";
		}
		if (claimSearchDto.getPayerAllowedProcedureCode() != null && claimSearchDto.getPayerAllowedProcedureCode() !="") {
			query += " AND icl.payer_allowed_procedure_code::text ilike" + "'%" + claimSearchDto.getPayerAllowedProcedureCode() + "%'";
		}
		if (claimSearchDto.getSubmittedUnits() != null) {
			query += " AND icl.submitted_units::text ilike" + "'%" + claimSearchDto.getSubmittedUnits() + "%'";
		}
		if (claimSearchDto.getDiags() != null && claimSearchDto.getDiags() != "") {
			String Diags = claimSearchDto.getDiags();
			String[] DiagsArray = Diags.split(",");
			query += " AND (";
			for (int i = 0; i < DiagsArray.length; i++) {
				if (DiagsArray[i].contains(".")) {
					String[] diagss = DiagsArray[i].split("\\.");
					query += "ic.diags::text like " + "'%" + diagss[0] + diagss[1] + "%'  or ";
					query += "ic.admitting_diags::text ilike " + "'%" + diagss[0] + diagss[1] + "%'  or ";
					query += "ic.principal_diags::text ilike " + "'%" + diagss[0] + diagss[1] + "%'  or ";
					query += "ic.external_cause_of_injury_diags::text ilike " + "'%" + diagss[0] + diagss[1] + "%'";
				} else {
					query += "ic.diags::text like " + "'%" + DiagsArray[i] + "%'  or ";
					query += "ic.admitting_diags::text ilike " + "'%" + DiagsArray[i] + "%'  or ";
					query += "ic.principal_diags::text ilike " + "'%" + DiagsArray[i] + "%'  or ";
					query += "ic.external_cause_of_injury_diags::text ilike " + "'%" + DiagsArray[i] + "%'";
				}
				if (!(i == DiagsArray.length - 1)) {
					query += " or ";
				}
			}
			query += ")";
		}
		if (claimSearchDto.getDiagsF() != null && claimSearchDto.getDiagsF() != "") {
			String Diags = claimSearchDto.getDiagsF();
			String[] DiagsArray = Diags.split(",");
			query += " AND (";
			for (int i = 0; i < DiagsArray.length; i++) {
				if (DiagsArray[i].contains(".")) {
					String[] diagss = DiagsArray[i].split("\\.");
					query += "ic.diags::text ilike " + "'%" + diagss[0] + diagss[1] + "%'";
				} else {
					query += "ic.diags::text ilike " + "'%" + DiagsArray[i] + "%'";
				}
				if (!(i == DiagsArray.length - 1)) {
					query += " or ";
				}
			}
			query += ")";
		}
		if (claimSearchDto.getAdmittingDiagsF() != null && claimSearchDto.getAdmittingDiagsF() != "") {
			query += " AND ic.admitting_diags::text ilike" + "'%" + claimSearchDto.getAdmittingDiagsF() + "%'";
		}
		if (claimSearchDto.getReprocessed() != null && claimSearchDto.getReprocessed().toString() != "") {
			query += " AND ic.reprocessed::text ilike" + "'%" + claimSearchDto.getReprocessed() + "%'";
		}

		if (claimSearchDto.getClientCode() != null && claimSearchDto.getClientCode() != "") {
			query += " AND ic.client_code::text ilike" + "'%" + claimSearchDto.getClientCode() + "%'";
		}

		if (claimSearchDto.getRenderingTaxonomy() != null && claimSearchDto.getRenderingTaxonomy() != "") {
			query += " AND ic.rendering_taxonomy::text ilike" + "'%" + claimSearchDto.getRenderingTaxonomy() + "%'";
		}

		if (claimSearchDto.getLineLevelTaxonomy()!= null && claimSearchDto.getLineLevelTaxonomy() != "") {
			query += " AND icl.line_level_taxonomy::text ilike" + "'%" + claimSearchDto.getLineLevelTaxonomy() + "%'";
		}
		if (claimSearchDto.getTaxIdentifier() != null && claimSearchDto.getTaxIdentifier() != "") {
			query += " AND ic.tax_identifier::text ilike" + "'%" + claimSearchDto.getTaxIdentifier() + "%'";
		}

		if (claimSearchDto.getRenderingProviderNpi() != null && claimSearchDto.getRenderingProviderNpi() != "") {
			query += " AND ic.rendering_provider_npi::text ilike" + "'%" + claimSearchDto.getRenderingProviderNpi() + "%'";
		}
		if (claimSearchDto.getBillingProviderId() != null && claimSearchDto.getBillingProviderId().toString() != "") {
			query += " AND ic.billing_provider_id::text ilike" + "'%" + claimSearchDto.getBillingProviderId() + "%'";
		}

		if (claimSearchDto.getDx_code_1() != null && !claimSearchDto.getDx_code_1().isEmpty()) {
			String dxCode1 = claimSearchDto.getDx_code_1().replace(".", ""); // remove any dots
			query += " AND icl.dx_code_1::text ILIKE '%" + dxCode1 + "%'";
		}

		if (claimSearchDto.getDx_code_2() != null && claimSearchDto.getDx_code_2() != "") {
			String dxCode2 = claimSearchDto.getDx_code_2().replace(".", "");
			query += " AND icl.dx_code_2::text ilike" + "'%" + dxCode2 + "%'";
		}

		if (claimSearchDto.getDx_code_3() != null && claimSearchDto.getDx_code_3() != "") {
			String dxCode3 = claimSearchDto.getDx_code_3().replace(".", "");
			query += " AND icl.dx_code_3::text ilike" + "'%" + dxCode3 + "%'";
		}

		if (claimSearchDto.getDx_code_4() != null && claimSearchDto.getDx_code_4() != "") {
			String dxCode4 = claimSearchDto.getDx_code_4().replace(".", "");
			query += " AND icl.dx_code_4::text ilike" + "'%" + dxCode4 + "%'";
		}

		if (claimSearchDto.getDiagnosisCodes() != null && claimSearchDto.getDiagnosisCodes() != "") {
			String diagnosis_codes = claimSearchDto.getDiagnosisCodes().replace(".", "");
			query += " AND ic.diagnosis_codes::text ilike" + "'%" + diagnosis_codes + "%'";
		}
		if (claimSearchDto.getExternalCauseOfInjuryDiagsF() != null
				&& claimSearchDto.getExternalCauseOfInjuryDiagsF() != "") {
			query += " AND ic.external_cause_of_injury_diags::text ilike" + "'%"
					+ claimSearchDto.getExternalCauseOfInjuryDiagsF() + "%'";
		}
		if (claimSearchDto.getMedicalPolicyF() != null && claimSearchDto.getMedicalPolicyF() != "") {
			String mpQuery = " select * from policy.medical_policies where medical_policy_title::text ilike " + "'%"
					+ claimSearchDto.getMedicalPolicyF() + "%'";
			if (mpQuery != null) {
				List<MedicalPolicyLookup> mpKey = ipuNamedParameterJdbcTemplate.query(mpQuery,
						new BeanPropertyRowMapper(MedicalPolicyLookup.class));
				if (mpKey != null) {
					query += " AND (";
					for (int i = 0; i < mpKey.size(); i++) {
						MedicalPolicyLookup mplkp = modelMapper.map(mpKey.get(i), MedicalPolicyLookup.class);
						query += " p.medical_policy_key_fk = " + mplkp.getMedicalPolicyKey();
						if (!(i == mpKey.size() - 1)) {
							query += " OR ";
						}
					}
					query += ")";
				}
			}
		}
		if (claimSearchDto.getProcessedOn() != null && claimSearchDto.getProcessedOn() != "") {
			query += " AND icl.created_date::text ilike" + "'%" + claimSearchDto.getProcessedOn() + "%'";
		}
		if (claimSearchDto.getRevenueCode() != null && claimSearchDto.getRevenueCode() != "") {
			query += " AND icl.revenue_code::text ilike " + "'%" + claimSearchDto.getRevenueCode() + "%'";
		}
		if (claimSearchDto.getPayerAllowedRevenueCode() != null && claimSearchDto.getPayerAllowedRevenueCode() != "") {
			query += " AND icl.payer_allowed_revenue_code::text ilike " + "'%" + claimSearchDto.getPayerAllowedRevenueCode() + "%'";
		}
		if (claimSearchDto.getSocPostalCode() != null && claimSearchDto.getSocPostalCode() !="") {
			query += " AND ic.soc_postal_code::text ilike" + "'%" + claimSearchDto.getSocPostalCode() + "%'";
		}
		if (claimSearchDto.getBillingPostalCode() != null && claimSearchDto.getBillingPostalCode() !="") {
			query += " AND ic.billing_postal_code::text ilike" + "'%" + claimSearchDto.getBillingPostalCode() + "%'";
		}
		if (claimSearchDto.getSocProviderId() != null) {
			query += " AND ic.soc_provider_id::text ilike" + "'%" + claimSearchDto.getSocProviderId() + "%'";
		}
		if (claimSearchDto.getRvuPrice() != null) {
			query += " AND icl.rvu_price::text ilike" + "'%" + claimSearchDto.getRvuPrice() + "%'";
		}
		return query;
	}

}