package com.amps.policy.config.queries;

public class StageTaxIdDeltaQuery {
    public static final String StageTaxIdDeltaQuery = """
            INSERT INTO etl.client_tin_exclusions_delta
            	select * from (SELECT 'ADD' AS delta_action,policy_id,client_group_id,
            						   lob,claim_type,tax_id,deleted_b FROM etl.client_tin_exclusions_stage
            						   WHERE policy_id = :policyId AND (client_group_id, lob, claim_type, tax_id) IN (
            						   (SELECT client_group_id, lob, claim_type, tax_id
            						   FROM etl.client_tin_exclusions_stage WHERE policy_id = :policyId)
            						   EXCEPT (SELECT client_group_id, lob, claim_type, tax_id
            						   FROM policy.client_tin_exclusions WHERE policy_id_fk = :policyId))
            						   UNION
            						    SELECT 'DELETE' AS delta_action,policy_id_fk,client_group_id,
            								   lob,claim_type,tax_id,deleted_b FROM policy.client_tin_exclusions
            								   WHERE policy_id_fk = :policyId AND deleted_b = 0 AND (client_group_id, lob, claim_type, tax_id) IN (
            								   (SELECT client_group_id, lob, claim_type, tax_id
            								   FROM policy.client_tin_exclusions WHERE policy_id_fk = :policyId and deleted_b = 0)
            								   EXCEPT (SELECT client_group_id, lob, claim_type, tax_id
            								   FROM etl.client_tin_exclusions_stage WHERE policy_id = :policyId))
            						   UNION
                                        SELECT 'DELETE' AS delta_action, ctes.policy_id, ctes.client_group_id, ctes.lob,
                                               ctes.claim_type, ctes.tax_id, ctes.deleted_b
                                        FROM etl.client_tin_exclusions_stage ctes
                                        JOIN policy.client_tin_exclusions cte
                                            ON ctes.policy_id = cte.policy_id_fk
                                            AND ctes.client_group_id = cte.client_group_id
                                            AND ctes.lob = cte.lob
                                            AND ctes.claim_type = cte.claim_type
                                            AND ctes.tax_id = cte.tax_id
                                        WHERE ctes.deleted_b != cte.deleted_b
                                        AND ctes.policy_id = :policyId
                                        AND cte.deleted_b = 0
                                        AND NOT EXISTS (
                                            SELECT 1 FROM policy.client_tin_exclusions p
                                            WHERE p.policy_id_fk = ctes.policy_id
                                            AND p.client_group_id = ctes.client_group_id
                                            AND p.lob = ctes.lob
                                            AND p.claim_type = ctes.claim_type
                                            AND p.tax_id = ctes.tax_id
                                            AND p.deleted_b = 1
                                        )
                                        UNION
                                        SELECT 'ADD' AS delta_action, ctes.policy_id, ctes.client_group_id, ctes.lob,
                                               ctes.claim_type, ctes.tax_id, ctes.deleted_b
                                        FROM etl.client_tin_exclusions_stage ctes
                                        JOIN policy.client_tin_exclusions cte
                                            ON ctes.policy_id = cte.policy_id_fk
                                            AND ctes.client_group_id = cte.client_group_id
                                            AND ctes.lob = cte.lob
                                            AND ctes.claim_type = cte.claim_type
                                            AND ctes.tax_id = cte.tax_id
                                        WHERE ctes.deleted_b != cte.deleted_b
                                        AND ctes.policy_id = :policyId
                                        AND cte.deleted_b = 1
                                        AND NOT EXISTS (
                                            SELECT 1 FROM policy.client_tin_exclusions p
                                            WHERE p.policy_id_fk = ctes.policy_id
                                            AND p.client_group_id = ctes.client_group_id
                                            AND p.lob = ctes.lob
                                            AND p.claim_type = ctes.claim_type
                                            AND p.tax_id = ctes.tax_id
                                            AND p.deleted_b = 0
                                        )
            				  ) as t;
            	""";

    public static final String TaxIdExceptionsQuery = """
               SELECT ctes.*, 'Invalid Client' AS reason
                 									FROM etl.client_tin_exclusions_stage ctes
                 									WHERE ctes.policy_id = :policyId and ctes.client_group_id NOT IN (
                 									SELECT client_group_id FROM policy.client_assignment WHERE policy_id = :policyId
                 									)
                 									union
                 SELECT *, 'Invalid Code' AS reason
                 FROM etl.client_tin_exclusions_stage ctes
                 WHERE ctes.deleted_b = 1
                   AND ctes.policy_id = :policyId
                   AND NOT EXISTS (
                       SELECT 1
                       FROM policy.client_tin_exclusions cte
                       WHERE cte.policy_id_fk = ctes.policy_id
                         AND cte.client_group_id = ctes.client_group_id
                         AND cte.claim_type = ctes.claim_type
                         AND cte.lob = ctes.lob
                         AND cte.tax_id = ctes.tax_id
                         AND cte.deleted_b = 0
                   )
                   AND NOT EXISTS (
                       SELECT 1
                       FROM policy.client_tin_exclusions cte
                       WHERE cte.policy_id_fk = ctes.policy_id
                         AND cte.client_group_id = ctes.client_group_id
                         AND cte.claim_type = ctes.claim_type
                         AND cte.lob = ctes.lob
                         AND cte.tax_id = ctes.tax_id
                         AND cte.deleted_b = 1
                   );
                 
            """;

    public static final String TaxIdQuery = "select * from etl.client_tin_exclusions_delta where policy_id=:policyId";
}