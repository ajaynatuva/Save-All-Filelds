package com.amps.policy.config.queries;

public class StageNpiDeltaQuery {

	public static final String StageNpiDeltaQuery = """
			INSERT INTO etl.client_npi_exclusions_delta
			           	select * from (SELECT 'ADD' AS delta_action,policy_id_fk as policyId,client_group_id,
			           				   lob,claim_type,npi,deleted_b FROM etl.client_npi_exclusions_stage
			           				   WHERE policy_id_fk = :policyId AND (client_group_id, lob, claim_type, npi) IN (
			           				   (SELECT client_group_id, lob, claim_type, npi
			           				   FROM etl.client_npi_exclusions_stage WHERE policy_id_fk =:policyId)
			           				   EXCEPT (SELECT client_group_id, lob, claim_type, npi
			           				   FROM policy.client_npi_exclusions WHERE policy_id_fk =:policyId))
			           				   UNION
			           				    SELECT 'DELETE' AS delta_action,policy_id_fk as policyId,client_group_id,
			           					   lob,claim_type,npi,deleted_b FROM policy.client_npi_exclusions
			           					   WHERE policy_id_fk =:policyId AND deleted_b = 0 AND (client_group_id, lob, claim_type, npi) IN (
			           					   (SELECT client_group_id, lob, claim_type, npi
			           					   FROM policy.client_npi_exclusions WHERE policy_id_fk =:policyId and deleted_b = 0)
			           					   EXCEPT (SELECT client_group_id, lob, claim_type, npi
			           					   FROM etl.client_npi_exclusions_stage WHERE policy_id_fk =:policyId))
			           				   UNION
			                                      SELECT 'DELETE' AS delta_action, ctes.policy_id_fk as policyId, ctes.client_group_id, ctes.lob,
			                                             ctes.claim_type, ctes.npi, ctes.deleted_b
			                                      FROM etl.client_npi_exclusions_stage ctes
			                                      JOIN policy.client_npi_exclusions cte
			                                          ON ctes.policy_id_fk = cte.policy_id_fk
			                                          AND ctes.client_group_id = cte.client_group_id
			                                          AND ctes.lob = cte.lob
			                                          AND ctes.claim_type = cte.claim_type
			                                          AND ctes.npi = cte.npi
			                                      WHERE ctes.deleted_b != cte.deleted_b
			                                      AND ctes.policy_id_fk =:policyId
			                                      AND cte.deleted_b = 0
			                                      AND NOT EXISTS (
			                                          SELECT 1 FROM policy.client_npi_exclusions p
			                                          WHERE p.policy_id_fk = ctes.policy_id_fk
			                                          AND p.client_group_id = ctes.client_group_id
			                                          AND p.lob = ctes.lob
			                                          AND p.claim_type = ctes.claim_type
			                                          AND p.npi = ctes.npi
			                                          AND p.deleted_b = 1
			                                      )
			                                      UNION
			                                      SELECT 'ADD' AS delta_action, ctes.policy_id_fk as policyId, ctes.client_group_id, ctes.lob,
			                                             ctes.claim_type, ctes.npi, ctes.deleted_b
			                                      FROM etl.client_npi_exclusions_stage ctes
			                                      JOIN policy.client_npi_exclusions cte
			                                          ON ctes.policy_id_fk = cte.policy_id_fk
			                                          AND ctes.client_group_id = cte.client_group_id
			                                          AND ctes.lob = cte.lob
			                                          AND ctes.claim_type = cte.claim_type
			                                          AND ctes.npi = cte.npi
			                                      WHERE ctes.deleted_b != cte.deleted_b
			                                      AND ctes.policy_id_fk =:policyId
			                                      AND cte.deleted_b = 1
			                                      AND NOT EXISTS (
			                                          SELECT 1 FROM policy.client_npi_exclusions p
			                                          WHERE p.policy_id_fk = ctes.policy_id_fk
			                                          AND p.client_group_id = ctes.client_group_id
			                                          AND p.lob = ctes.lob
			                                          AND p.claim_type = ctes.claim_type
			                                          AND p.npi = ctes.npi
			                                          AND p.deleted_b = 0
			                                      )
			           		  ) as t;
			
			
				   		""";

	public static final String NpiExceptionsQuery = """
					SELECT policy_id_fk as policyId, cnes.*, 'Invalid Client' AS reason
			       									FROM etl.client_npi_exclusions_stage cnes
			       									WHERE cnes.policy_id_fk =:policyId and cnes.client_group_id NOT IN (
			       									SELECT client_group_id FROM policy.client_assignment WHERE policy_id =:policyId
			       									)
			       									union
			       SELECT policy_id_fk as policyId,*, 'Invalid Code' AS reason
			       FROM etl.client_npi_exclusions_stage cnes
			       WHERE cnes.deleted_b = 1
			         AND cnes.policy_id_fk =:policyId
			         AND NOT EXISTS (
			             SELECT 1
			             FROM policy.client_npi_exclusions cne
			             WHERE cne.policy_id_fk = cnes.policy_id_fk
			               AND cne.client_group_id = cnes.client_group_id
			               AND cne.claim_type = cnes.claim_type
			               AND cne.lob = cnes.lob
			               AND cne.npi = cnes.npi
			               AND cne.deleted_b = 0
			         )
			         AND NOT EXISTS (
			             SELECT 1
			             FROM policy.client_npi_exclusions cne
			             WHERE cne.policy_id_fk = cnes.policy_id_fk
			               AND cne.client_group_id = cnes.client_group_id
			               AND cne.claim_type = cnes.claim_type
			               AND cne.lob = cnes.lob
			               AND cne.npi = cnes.npi
			               AND cne.deleted_b = 1
			)
						""";

	public static final String NpiQuery = "select delta_action, policy_id_fk as policyId, client_group_id,lob,claim_type,npi from etl.client_npi_exclusions_delta where policy_id_fk =:policyId";
}
