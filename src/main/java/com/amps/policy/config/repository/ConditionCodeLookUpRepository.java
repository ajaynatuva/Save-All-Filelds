package com.amps.policy.config.repository;

import com.amps.policy.config.model.ConditionCodeLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ConditionCodeLookUpRepository extends JpaRepository<ConditionCodeLookUp, String> {

	@Query(value = "SELECT * FROM policy.cond_code_lkp WHERE cond_code_lkp.cond_code=:condcode", nativeQuery = true)
	List<ConditionCodeLookUp> findBycondCode(@Param("condcode") String condcode);

	@Query(value = "update policy.cond_code_lkp set cond_code_lkp.cond_desc=:condDesc,cond_code_lkp.start_date=:startDate,cond_code_lkp.end_date=:endDate where cond_code_lkp.cond_code=:condCode", nativeQuery = true)
	List<ConditionCodeLookUp> updatecondcode(@Param("condCode") String condcode);

}