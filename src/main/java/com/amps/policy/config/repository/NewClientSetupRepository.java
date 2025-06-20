package com.amps.policy.config.repository;

import com.amps.policy.config.dto.copyClientDTO;
import com.amps.policy.config.model.ClientAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewClientSetupRepository extends JpaRepository<ClientAssignment, String> {

    @Query(value = """
               SELECT c.client_group_id AS clientGroupId,
                      array_agg(p.policy_number) AS policyNumbers,
                      array_agg(p.policy_version) AS policyVersions,
                      array_agg(p.medical_policy_key_fk) AS medicalPolicyKeys,
                      array_agg(p.sub_policy_key_fk) AS subPolicyKeys,
                      array_agg(p.reason_code_fk) AS reasonCodes,
                      array_agg(c.policy_id) AS policyIds,
                      array_agg(c.client_start_date) AS clientStartDates,
                      array_agg(c.client_end_date) AS clientEndDates
                 FROM policy.client_assignment c
            LEFT JOIN policy.policy p ON p.policy_id = c.policy_id AND p.is_prod_b = 1
             GROUP BY c.client_group_id
            """, nativeQuery = true)
    List<Object[]> findAllClientGroupPolicies();

}
