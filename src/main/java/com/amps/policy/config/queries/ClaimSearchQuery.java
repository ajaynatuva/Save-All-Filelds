package com.amps.policy.config.queries;


import com.amps.policy.config.dto.ClaimSearchDTO;
import org.springframework.stereotype.Component;

@Component
public class ClaimSearchQuery {

	ClaimSearchDTO claimSearchDto;
	public static String ClaimQuery =
			"""
                         SELECT
                             ic.pos_or_bill_type,
                             ic.ipu_claim_run_status_id,
                             icl.revenue_code,
                             ic.client_group_type_code,
                             ic.clm_form_type,
                             ic.client_group_id,
                             ic.principal_diags,
                             icl.ipu_claim_line_id,
                             icl.drg_claim_id AS drgn_claim_id,
                             ic.diags,
                             ic.admitting_diags,
                             ic.external_cause_of_injury_diags,
                             icl.itemized_bill_line_id,
                             icl.drg_claim_sl_id,
                             icl.submitted_units,
                             icl.allowed_quantity,
                             ic.reprocessed,
                             ic.tax_identifier,
                             ic.rendering_taxonomy,
                             icl.rendering_taxonomy AS line_level_taxonomy,
                             ic.rendering_provider_npi,
                             ic.billing_provider_id,
                             ic.client_code,
                             ic.client_group,
                             icl.submitted_charge_amount,
                             icl.submitted_modifier_1,
                             icl.submitted_modifier_2,
                             ic.ipu_patient_id,
                             ic.diagnosis_codes,
                             ic.ipu_clm_type,
                             icl.submitted_modifier_3,
                             icl.submitted_modifier_4,
                             icl.submitted_procedure_code,
                             icl.dx_code_1,
                             icl.dx_code_2,
                             icl.dx_code_3,
                             icl.dx_code_4,
                             icl.dos_from,
                             icl.dos_to,
                             icl.payer_allowed_amount,
                             icl.payer_allowed_units,
                             icl.pos,
                             icl.rendering_provider_npi AS rendering_provider_npi_line_level,
                             icl.payer_allowed_modifier_1,
                             icl.payer_allowed_modifier_2,
                             icl.payer_allowed_modifier_3,
                             icl.payer_allowed_modifier_4,
                             icl.payer_allowed_procedure_code,
                             ic.soc_postal_code,
                             icl.drgn_challenge_amt,
                             ic.billing_postal_code,
                             ic.soc_provider_id,
                             icl.rvu_price,
                             icl.created_date::TIMESTAMP WITHOUT TIME ZONE,
                             icl.drgn_challenge_code,
                             icl.payer_allowed_revenue_code,
                             ic.cond_code,
                             STRING_AGG(irl.drgn_claim_id::TEXT, ',') AS ref_drgn_claim_id,
                             STRING_AGG(irl.drgn_claim_sl_id::TEXT, ',') AS ref_drgn_claim_sl_id,
                             ir.policy_id AS ref_policy_id,
                             ir.reason_code,
                             ir.challenge_code,
                             ir.amount,
                             ir.units,
                             CASE WHEN icl.drgn_challenge_amt IS NULL THEN 0.00 ELSE icl.drgn_challenge_amt END AS drgn_challenge_amt,
                             CASE WHEN ir.amount IS NULL THEN 0.00 ELSE ir.amount END AS ipu_challenge_amt,
                             ic.cond_code AS conditionCodes,
                             p.policy_id,
                             p.policy_number,
                             p.policy_version,
                    p.claim_type,
                    p.medical_policy_key_fk
                         FROM
                             (SELECT * FROM ipuclaims.ipu_claim_line ORDER BY created_date DESC {LIMIT_WITHOUT_COND} ) icl
                         INNER JOIN ipuclaims.ipu_claim ic
                             ON icl.ipu_claim_id = ic.ipu_claim_id
                         LEFT JOIN ipuclaims.ipu_recommendation ir
                             ON icl.ipu_claim_line_id = ir.ipu_claim_line_id
                         LEFT JOIN ipuclaims.ipu_recommendation_referenced_lines irl
                             ON irl.ipu_recommendation_id = ir.ipu_recommendation_id
                         LEFT JOIN policy.policy p
                             ON p.policy_id = ir.policy_id
                         WHERE 1=1 {CONDITION}
                         GROUP BY
                             ic.pos_or_bill_type,
                             ic.ipu_claim_run_status_id,
                             icl.revenue_code,
                             ic.client_group_type_code,
                             ic.clm_form_type,
                             ic.client_group_id,
                             ic.principal_diags,
                             icl.ipu_claim_line_id,
                             icl.drg_claim_id,
                             ic.diags,
                             ic.admitting_diags,
                             ic.external_cause_of_injury_diags,
                             icl.itemized_bill_line_id,
                             icl.drg_claim_sl_id,
                             icl.submitted_units,
                             icl.allowed_quantity,
                             ic.reprocessed,
                             ic.tax_identifier,
                             ic.rendering_taxonomy,
                             icl.rendering_taxonomy,
                             ic.rendering_provider_npi,
                             ic.billing_provider_id,
                             ic.client_code,
                             ic.client_group,
                             icl.submitted_charge_amount,
                             icl.submitted_modifier_1,
                             icl.submitted_modifier_2,
                             ic.ipu_patient_id,
                             ic.diagnosis_codes,
                             ic.ipu_clm_type,
                             icl.submitted_modifier_3,
                             icl.submitted_modifier_4,
                             icl.submitted_procedure_code,
                             icl.dx_code_1,
                             icl.dx_code_2,
                             icl.dx_code_3,
                             icl.dx_code_4,
                             icl.dos_from,
                             icl.dos_to,
                             icl.payer_allowed_amount,
                             icl.payer_allowed_units,
                             icl.pos,
                             icl.rendering_provider_npi,
                             icl.payer_allowed_modifier_1,
                             icl.payer_allowed_modifier_2,
                             icl.payer_allowed_modifier_3,
                             icl.payer_allowed_modifier_4,
                             icl.payer_allowed_procedure_code,
                             ic.soc_postal_code,
                             icl.drgn_challenge_amt,
                             ic.billing_postal_code,
                             ic.soc_provider_id,
                             icl.rvu_price,
                             icl.created_date::TIMESTAMP WITHOUT TIME ZONE,
                             icl.drgn_challenge_code,
                             icl.payer_allowed_revenue_code,
                             ic.cond_code,
                             ir.policy_id,
                             ir.reason_code,
                             ir.challenge_code,
                             ir.amount,
                             ir.units,
                             p.policy_id,
                             p.policy_number,
                             p.policy_version,
                    p.claim_type,
                    p.medical_policy_key_fk
                    {LIMIT_WITH_COND}
                    """;

	public static String countQuery = "select * from ipuclaims.ipu_claim_line";

	public static String numberOfRowsQuery = "select count(1) from (:count) as count";
}
