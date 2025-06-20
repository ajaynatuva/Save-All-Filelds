package com.amps.policy.config.repository;

import com.amps.policy.config.model.PolicyActionDiagnosisLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyActionDiagnosisLookUpRepository extends JpaRepository<PolicyActionDiagnosisLookUp, Integer>{
	

}
