package com.amps.policy.config.repository;

import com.amps.policy.config.model.AddOnCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddOnCodesRepository extends JpaRepository<AddOnCodes, Integer> {

	@Query(value = "select * from source.bo_policy_lkp", nativeQuery = true)
	public List<AddOnCodes> findAddOn();

}
