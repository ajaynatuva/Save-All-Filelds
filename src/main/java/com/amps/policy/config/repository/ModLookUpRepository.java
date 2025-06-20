package com.amps.policy.config.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amps.policy.config.model.ModLookUp;

@Repository
public interface ModLookUpRepository extends JpaRepository<ModLookUp, String> {

	@Query(value = "SELECT * FROM source.mod_lkp WHERE mod_lkp.cpt_mod=:cptMod", nativeQuery = true)
	List<ModLookUp> findByModlkp(@Param("cptMod") String cptMod);

	@Modifying
	@Transactional
	@Query(value = "update source.mod_lkp set description=:description,start_date=:startDate,end_date=:endDate,is_cci=:isCci,is_59_group=:is_59_group where cpt_mod=:cptMod and ambulance_mod_b=:ambulanceModB", nativeQuery = true)
	void updateModLkp(@Param("cptMod") String cptMod, @Param("description") String description,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("isCci") int isCci,
			@Param("is_59_group") int is_59_group, @Param("ambulanceModB") int ambulanceModB);
}
