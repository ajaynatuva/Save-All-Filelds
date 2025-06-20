package com.amps.policy.config.repository;

import com.amps.policy.config.model.StageProcedures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageProceduresRepository extends JpaRepository<StageProcedures, Integer> {

	@Query(value = "select * from StageProcedures where policyid.policyId =:id " ,nativeQuery = true)
	List<StageProcedures> findByPolicyid(@Param("id") int id);
	
//	@Query(value = "delete from StageProcedures where policyid.policyId =:id")
//	List<StageProcedures> deleteByPolicyid(@Param("id") int id);
//	
	
	

}
