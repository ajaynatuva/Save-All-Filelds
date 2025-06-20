package com.amps.policy.config.repository;

import com.amps.policy.config.model.CCI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CciRepository extends JpaRepository<CCI, Integer> {

	@Query(value = "select * from source.cci_lkp", nativeQuery = true)
	public List<CCI> findCci();

}
