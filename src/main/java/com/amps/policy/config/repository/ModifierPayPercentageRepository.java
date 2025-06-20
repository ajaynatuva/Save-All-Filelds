package com.amps.policy.config.repository;

import com.amps.policy.config.model.ModifierPayPercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModifierPayPercentageRepository extends JpaRepository<ModifierPayPercentage, Integer> {

	@Query(value = "select * from policy.modifier_pay_percentage where mpp_key_fk =:Key", nativeQuery = true)
	List<ModifierPayPercentage> ModifierPayPercentageData(@Param("Key") Integer Key);
}
