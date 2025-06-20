package com.amps.policy.config.queries;

public class StageTaxonomyDeltaQuery {
	public static final String StageTaxonomyDeltaQuery = """
			INSERT INTO etl.taxonomy_delta
						(delta_action, policy_id, client_group_id, taxonomy_code, spec_code, subspec_code, subspec_desc, function)
						SELECT 'ADD' AS delta_action, policy_id, client_group_id, taxonomy_code, spec_code, subspec_code, subspec_desc, function
						FROM etl.taxonomy_stage
						WHERE policy_id = :policyId
						AND (client_group_id, taxonomy_code, spec_code, subspec_code, policy_id)
						NOT IN (SELECT client_group_id, taxonomy_code, spec_code, subspec_code, policy_id FROM policy.taxonomy WHERE policy_id = :policyId)
						UNION
						SELECT 'DELETE' AS delta_action, policy_id, client_group_id, taxonomy_code, spec_code, subspec_code, subspec_desc, function
						FROM policy.taxonomy
						WHERE policy_id = :policyId and deleted_b = 0
						AND (client_group_id, taxonomy_code, spec_code, subspec_code, policy_id)
						NOT IN (SELECT client_group_id, taxonomy_code, spec_code, subspec_code, policy_id FROM etl.taxonomy_stage WHERE policy_id = :policyId)
						UNION
			SELECT 'DELETE' AS delta_action, ts.policy_id, ts.client_group_id, ts.taxonomy_code,\s
			             ts.spec_code, ts.subspec_code, ts.subspec_desc, ts.function
			      FROM etl.taxonomy_stage ts
			      JOIN policy.taxonomy tx
			          ON ts.policy_id = tx.policy_id
			          AND ts.client_group_id = tx.client_group_id
			          AND ts.spec_code = tx.spec_code
			          AND ts.subspec_code = tx.subspec_code
			          AND ts.taxonomy_code = tx.taxonomy_code
			      WHERE ts.deleted_b != tx.deleted_b
			      AND ts.policy_id = :policyId
			      AND tx.deleted_b = 0
			      AND NOT EXISTS (
			          SELECT 1 FROM policy.taxonomy p
			          WHERE p.policy_id = ts.policy_id
			          AND p.client_group_id = ts.client_group_id
			          AND p.spec_code = ts.spec_code
			          AND p.subspec_code = ts.subspec_code
			          AND p.taxonomy_code = ts.taxonomy_code
			          AND p.deleted_b = 1
			      )
			      union
			      SELECT 'ADD' AS delta_action, ts.policy_id, ts.client_group_id, ts.taxonomy_code,\s
			                ts.spec_code, ts.subspec_code, ts.subspec_desc, ts.function
			         FROM etl.taxonomy_stage ts
			         JOIN policy.taxonomy tx
			             ON ts.policy_id = tx.policy_id
			             AND ts.client_group_id = tx.client_group_id
			             AND ts.spec_code = tx.spec_code
			             AND ts.subspec_code = tx.subspec_code
			             AND ts.taxonomy_code = tx.taxonomy_code
			         WHERE ts.deleted_b != tx.deleted_b
			         AND ts.policy_id = :policyId
			         AND tx.deleted_b = 1
			         AND NOT EXISTS (
			             SELECT 1 FROM policy.taxonomy p
			             WHERE p.policy_id = ts.policy_id
			             AND p.client_group_id = ts.client_group_id
			             AND p.spec_code = ts.spec_code
			             AND p.subspec_code = ts.subspec_code
			             AND p.taxonomy_code = ts.taxonomy_code
			             AND p.deleted_b = 0
			         )
			""";

	public static final String TaxnomyExceptionsQuery = """

			SELECT ts.*, 'Invalid Client' AS reason
							FROM etl.taxonomy_stage ts
							WHERE ts.policy_id = :policyId
							AND (
							(ts.function = 0 AND ts.client_group_id <> 0 AND ts.client_group_id NOT IN (
							SELECT client_group_id FROM policy.client_assignment\s
							WHERE policy_id = ts.policy_id
							))
							OR
							(ts.function = 1 AND ts.client_group_id <> 0 AND NOT EXISTS (
							SELECT 1 FROM policy.client_assignment ca
							WHERE ca.client_group_id = ts.client_group_id\s
							AND ca.policy_id = ts.policy_id
							))
							)

							UNION

							SELECT ts.*, 'Invalid Taxonomy Code' AS reason\s
							FROM etl.taxonomy_stage ts
							WHERE ts.policy_id = :policyId
							AND (ts.taxonomy_code, ts.spec_code, ts.subspec_code) IN (
							SELECT taxonomy_code, spec_code, subspec_code\s
							FROM etl.taxonomy_stage\s
							WHERE policy_id = ts.policy_id
							EXCEPT\s
							SELECT taxonomy_code, spec_code, subspec_code\s
							FROM policy.subspec_map\s
							WHERE policy_id = ts.policy_id
							)

							UNION

							SELECT ts.*, 'Invalid Record' AS reason
			                       FROM etl.taxonomy_stage ts
			                       WHERE ts.policy_id = :policyId
			                       AND ts.taxonomy_code NOT IN (
			                           SELECT pt.taxonomy_code
			                           FROM policy.taxonomy pt
			                           WHERE pt.policy_id = ts.policy_id
			                           AND pt.function = 1
			                       )
			                       AND ts.function = 0
			                       AND NOT EXISTS (
			                           SELECT 1
			                           FROM etl.taxonomy_stage ts2
			                           WHERE ts2.policy_id = ts.policy_id
			                           AND ts2.taxonomy_code = ts.taxonomy_code
			                           AND ts2.function = 1
			                       )

							UNION

							SELECT ts.*, 'Invalid Function' AS reason
			                               FROM etl.taxonomy_stage ts
			                               WHERE ts.policy_id = :policyId
			                               AND (
			                                   (ts.client_group_id = 0 AND ts.function = 0)
			                                   OR (ts.client_group_id <> 0 AND ts.function = 1)
			                               )
			                               """;

	public static final String TaxonomyQuery = "select * from etl.taxonomy_delta where policy_id=:policyId";

}
