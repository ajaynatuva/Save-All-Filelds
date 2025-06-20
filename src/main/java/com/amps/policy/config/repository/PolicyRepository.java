package com.amps.policy.config.repository;

import com.amps.policy.config.model.Diagnosis;
import com.amps.policy.config.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	Policy findByPolicyId(Integer policyId);

	Policy findByPolicyNumber(int policynumber);

    @Query("SELECT p FROM Policy p WHERE p.policyNumber = :policyNumber AND CAST(p.policyVersion AS string) = :version")
    List<Policy> findByPolicynumber(@Param("policyNumber") int policyNumber, @Param("version") String version);


	@Query(value = "delete from policy.policy_dx where policy_diagnosis_key = :id", nativeQuery = true)
	List<Diagnosis> deleteDiagnosisData(@Param("id") int id);

	@Query(value = "select policy.policy_version from policy.policy where policy_number =:policyNumber order by policy_version DESC LIMIT 1", nativeQuery = true)
	Integer getLatestVersion(int policyNumber);

	@Query(value = "SELECT NEXTVAL('policy.policy_policy_number_seq')", nativeQuery = true)
	Integer getSeqValue();

	@Query(value = """
			SELECT p1.policy_number || '.' || p1.policy_version AS clonedPolVer 
			FROM policy.policy p LEFT JOIN policy.policy p1 
			ON p1.policy_id = p.cloned_policy_id WHERE p.policy_id =:policyId
			""", nativeQuery = true)
	String findByPolicyVersion(int policyId);
	
	@Query(value = "select * from policy.policy where policy_id =:policyId",nativeQuery = true)
	Policy getBypolicyData(int policyId);
}
