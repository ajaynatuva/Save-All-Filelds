package com.amps.policy.config.repository;

import com.amps.policy.config.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer>{
	
	@Query(value = "SELECT * FROM policy.policy_dx where policy_id =:policy_id", nativeQuery = true)
	List<Diagnosis> getDiagnosisData(@Param("policy_id") int policy_id );
	
	@Query(value = "delete from policy.policy_dx where policy_diagnosis_key =:id", nativeQuery = true)
	List<Diagnosis> deleteDiagnosisData(@Param("id") int id );
	
}
