package com.amps.policy.config.repository;

import java.util.List;

import com.amps.policy.config.model.CCIDeviationsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amps.policy.config.model.CCIDeviations;
import org.springframework.stereotype.Repository;

@Repository
public interface CciDeviationRepository extends JpaRepository<CCIDeviations, Integer> {

	@Query(value = "select * from source.cci_deviations order by column_i,column_ii asc",nativeQuery = true)
	public List<CCIDeviations> getCciDeviationsData();



}
