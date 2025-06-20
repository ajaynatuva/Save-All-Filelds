package com.amps.policy.config.queries;

public class StageProceduresDeltaQuery {
	public static final String StageProceduresDeltaQuery = """
						SELECT * FROM (
			    SELECT
			        'ADD' AS ACTION,
			        sp.policy_id,
			        sp.cpt_from,
			        sp.cpt_to,
			        sp.mod1,
			        sp.mod2,
			        sp.mod3,
			        sp.rev_from,
			        sp.rev_to,
			        sp.pos,
			        sp.days_lo,
			        sp.dos_from,
			        sp.dos_to,
			        sp.days_hi,
			        sp.action_fk,
			        sp.exclude_b,
			        sp.dx_link,
			        sp.clm_link_fk
			    FROM etl.stage_policy_procedures sp
			    LEFT JOIN policy.policy_procedures pp
			        ON sp.policy_id = pp.policy_id
			        AND sp.cpt_from = pp.cpt_from
			        AND sp.mod1 = pp.mod1
			    WHERE sp.policy_id =:policyId
			    AND pp.policy_id IS NULL

			    UNION ALL

			    SELECT
			        'DELETE' AS ACTION,
			        pp.policy_id,
			        pp.cpt_from,
			        pp.cpt_to,
			        pp.mod1,
			        pp.mod2,
			        pp.mod3,
			        pp.rev_from,
			        pp.rev_to,
			        pp.pos,
			        pp.days_lo,
			        pp.dos_from,
			        pp.dos_to,
			        pp.days_hi,
			        pp.action_fk,
			        pp.exclude_b,
			        pp.dx_link,
			        pp.clm_link_fk
			    FROM policy.policy_procedures pp
			    LEFT JOIN etl.stage_policy_procedures sp
			        ON pp.policy_id = sp.policy_id
			        AND pp.cpt_from = sp.cpt_from
			        AND pp.mod1 = sp.mod1
			    WHERE pp.policy_id =:policyId
			    AND sp.policy_id IS NULL  -- Records in policy but not in stage

			    UNION ALL

			SELECT DISTINCT
			        'CHANGE_OLD' AS ACTION,
			        pp.policy_id,
			        pp.cpt_from,
			        pp.cpt_to,
			        pp.mod1,
			        pp.mod2,
			        pp.mod3,
			        pp.rev_from,
			        pp.rev_to,
			        pp.pos,
			        pp.days_lo,
			        pp.dos_from,
			        pp.dos_to,
			        pp.days_hi,
			        pp.action_fk,
			        pp.exclude_b,
			        pp.dx_link,
			        pp.clm_link_fk
			    FROM policy.policy_procedures pp
			    JOIN etl.stage_policy_procedures sp
			        ON pp.policy_id = sp.policy_id
			        AND pp.cpt_from = sp.cpt_from
			        AND pp.mod1 = sp.mod1
			    WHERE pp.policy_id =:policyId
			    AND (
			        pp.rev_to != sp.rev_to OR
			        pp.cpt_to != sp.cpt_to OR
			        pp.days_hi != sp.days_hi OR
			        pp.dos_from != sp.dos_from or
			        pp.dos_to != sp.dos_to or
			        pp.days_lo != sp.days_lo OR
			        pp.exclude_b != sp.exclude_b OR
			        pp.dx_link != sp.dx_link OR
			        pp.action_fk != sp.action_fk OR
			        pp.clm_link_fk != sp.clm_link_fk OR
			        pp.mod3 != sp.mod3

			    )
			    AND NOT EXISTS (
			        SELECT 1
			        FROM etl.stage_policy_procedures sp2
			        WHERE pp.policy_id = sp2.policy_id
			        AND pp.cpt_from = sp2.cpt_from
			        AND (
			            pp.rev_to = sp2.rev_to
			            AND pp.cpt_to = sp2.cpt_to
			             AND pp.dos_from = sp2.dos_from
						AND PP.MOD1=sp2.MOD1
						and pp.dos_to = sp2.dos_to

			        )
			    )
			    UNION ALL
			    SELECT DISTINCT
			        'CHANGE_NEW' AS ACTION,
			        sp.policy_id,
			        sp.cpt_from,
			        sp.cpt_to,
			        sp.mod1,
			        sp.mod2,
			        sp.mod3,
			        sp.rev_from,
			        sp.rev_to,
			        sp.pos,
			        sp.days_lo,
			        sp.dos_from,
			        sp.dos_to,
			        sp.days_hi,
			        sp.action_fk,
			        sp.exclude_b,
			        sp.dx_link,
			        sp.clm_link_fk
			    FROM etl.stage_policy_procedures sp
			    JOIN policy.policy_procedures pp
			        ON sp.policy_id = pp.policy_id
			        AND sp.cpt_from = pp.cpt_from
					AND sp.mod1 = pp.mod1
			    WHERE sp.policy_id =:policyId
			    AND (
			      sp.rev_to != pp.rev_to OR
			        sp.cpt_to != pp.cpt_to OR
			        sp.days_hi != pp.days_hi OR
			        sp.dos_from != pp.dos_from OR
			        sp.dos_to != pp.dos_to or
			        sp.days_lo != pp.days_lo OR
			        sp.exclude_b != pp.exclude_b OR
			        sp.dx_link != pp.dx_link OR
			        sp.action_fk != pp.action_fk OR
			        sp.clm_link_fk != pp.clm_link_fk OR
			         sp.mod3 != pp.mod3

			    )
			    AND NOT EXISTS (
			        SELECT 1
			        FROM policy.policy_procedures pp2
			        WHERE sp.policy_id = pp2.policy_id
			        AND sp.cpt_from = pp2.cpt_from
			        AND (
			            sp.rev_to = pp2.rev_to
			            AND sp.cpt_to = pp2.cpt_to
			            AND sp.dos_from = pp2.dos_from
						AND sp.dos_to = pp2.dos_to
						AND SP.MOD1=pp2.MOD1

			        )
			    )
				)
				AS DELTA
			ORDER BY( CASE
			    WHEN ACTION = 'ADD' THEN 0
				WHEN ACTION = 'DELETE' THEN 1
			    WHEN ACTION = 'CHANGE_NEW' THEN 3
			    WHEN ACTION = 'CHANGE_OLD' THEN 3

			END)
			,policy_id, cpt_from, mod1, mod2, rev_from;
						""";
}
