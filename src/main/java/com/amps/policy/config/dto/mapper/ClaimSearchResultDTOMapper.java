package com.amps.policy.config.dto.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;

import org.springframework.jdbc.core.RowMapper;

import com.amps.policy.config.dto.ClaimSearchResultDTO;

public class ClaimSearchResultDTOMapper implements RowMapper<ClaimSearchResultDTO> {

	@Override
	public ClaimSearchResultDTO mapRow(ResultSet resultSet, int count) throws SQLException {
		ClaimSearchResultDTO dto = new ClaimSearchResultDTO();
		ZoneId databaseTimeZone = ZoneId.of("-05:00");

		// Handling Timestamp conversion properly
		Timestamp timestamp = resultSet.getTimestamp("created_date");
		if (timestamp != null) {
			dto.setCreatedDate(timestamp.toInstant().atZone(databaseTimeZone).toOffsetDateTime().toString());
		} else {
			dto.setCreatedDate(resultSet.getString("created_date"));
		}

		// Assigning values from resultSet to DTO
		dto.setPolicyId(resultSet.getInt("policy_id"));
		dto.setPolicyNumber(resultSet.getInt("policy_number"));
		dto.setPolicyVersion(resultSet.getInt("policy_version"));
		dto.setReasonCode(resultSet.getString("reason_code"));
		dto.setDrgnClaimId(resultSet.getInt("drgn_claim_id"));
		dto.setMedicalPolicyKeyFk(resultSet.getInt("medical_policy_key_fk"));
		dto.setItemizedBillLineId(resultSet.getInt("itemized_bill_line_id"));

		// Handle nullable integer values correctly
		Integer claimSlId = (Integer) resultSet.getObject("drg_claim_sl_id");
		dto.setClaimSlId(claimSlId != null ? claimSlId : null);

		dto.setRefDrgnClaimId(resultSet.getString("ref_drgn_claim_id"));
		dto.setSubmitted_modifier_1(resultSet.getString("submitted_modifier_1"));
		dto.setSubmitted_modifier_2(resultSet.getString("submitted_modifier_2"));
		dto.setSubmitted_modifier_3(resultSet.getString("submitted_modifier_3"));
		dto.setSubmitted_modifier_4(resultSet.getString("submitted_modifier_4"));
		dto.setSubmittedChargeAmount(resultSet.getString("submitted_charge_amount"));
		dto.setSubmittedProcedureCode(resultSet.getString("submitted_procedure_code"));
		dto.setDosFrom(resultSet.getDate("dos_from"));
		dto.setDosTo(resultSet.getDate("dos_to"));

		dto.setDrgnChallengeCode(resultSet.getInt("drgn_challenge_code"));
		dto.setIpuChallengeCode(resultSet.getInt("challenge_code"));
		dto.setDrgnChallengeAmt(resultSet.getDouble("drgn_challenge_amt"));
		dto.setIpuChallengeAmt(resultSet.getString("ipu_challenge_amt"));
		dto.setRefDrgnClaimSlId(resultSet.getString("ref_drgn_claim_sl_id"));
		dto.setAllowedQuantity(resultSet.getString("allowed_quantity"));
		dto.setSubmittedUnits(resultSet.getString("submitted_units"));
		dto.setPrincipalDiags(resultSet.getString("principal_diags"));
		dto.setAdmittingDiags(resultSet.getString("admitting_diags"));
		dto.setExternalCauseOfInjuryDiags(resultSet.getString("external_cause_of_injury_diags"));
		dto.setPosOrBillType(resultSet.getString("pos_or_bill_type"));
		dto.setIpuPatientId(resultSet.getInt("ipu_patient_id"));

		dto.setClmFormType(resultSet.getString("clm_form_type"));
		dto.setIpuClmType(resultSet.getString("ipu_clm_type"));
		dto.setIpuClaimRunStatusId(resultSet.getString("ipu_claim_run_status_id"));
		dto.setReprocessed(resultSet.getInt("reprocessed"));
		dto.setBillingProviderId(resultSet.getInt("billing_provider_id"));
		dto.setClientGroup(resultSet.getString("client_group"));
		dto.setClientCode(resultSet.getString("client_code"));
		dto.setRenderingTaxonomy(resultSet.getString("rendering_taxonomy"));
		dto.setLineLevelTaxonomy(resultSet.getString("line_level_taxonomy"));
		dto.setRenderingProviderNpi(resultSet.getString("rendering_provider_npi"));
		dto.setTaxIdentifier(resultSet.getString("tax_identifier"));

		dto.setDx_code_1(resultSet.getString("dx_code_1"));
		dto.setDx_code_2(resultSet.getString("dx_code_2"));
		dto.setDx_code_3(resultSet.getString("dx_code_3"));
		dto.setDx_code_4(resultSet.getString("dx_code_4"));
		dto.setDiags(resultSet.getString("diags"));
		dto.setDiagnosisCodes(resultSet.getString("diagnosis_codes"));
		dto.setConditionCodes(resultSet.getString("conditionCodes"));
		dto.setRevenueCode(resultSet.getString("revenue_code"));

		dto.setPayerAllowedModifier1(resultSet.getString("payer_allowed_modifier_1"));
		dto.setPayerAllowedModifier2(resultSet.getString("payer_allowed_modifier_2"));
		dto.setPayerAllowedModifier3(resultSet.getString("payer_allowed_modifier_3"));
		dto.setPayerAllowedModifier4(resultSet.getString("payer_allowed_modifier_4"));
		dto.setPayerAllowedAmount(resultSet.getString("payer_allowed_amount"));
		dto.setPlaceOfService(resultSet.getString("pos"));
		dto.setPayerAllowedUnits(resultSet.getString("payer_allowed_units"));
		dto.setRenderingProviderNpiLineLevel(resultSet.getString("rendering_provider_npi_line_level"));
		dto.setPayerAllowedProcedureCode(resultSet.getString("payer_allowed_procedure_code"));
		dto.setSocPostalCode(resultSet.getString("soc_postal_code"));
		dto.setBillingPostalCode(resultSet.getString("billing_postal_code"));
		dto.setSocProviderId(resultSet.getInt("soc_provider_id"));
		dto.setRvuPrice(resultSet.getDouble("rvu_price"));
		dto.setPayerAllowedRevenueCode(resultSet.getString("payer_allowed_revenue_code"));

		return dto;
	}

}
