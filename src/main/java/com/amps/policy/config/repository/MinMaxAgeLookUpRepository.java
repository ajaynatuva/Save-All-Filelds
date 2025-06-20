package com.amps.policy.config.repository;

import com.amps.policy.config.model.MinMaxAgeLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MinMaxAgeLookUpRepository extends JpaRepository<MinMaxAgeLookUp, Integer> {
	
	@Query(value="SELECT * FROM policy.min_max_age_lkp WHERE min_max_age_lkp.min_max_age_key=:minMaxAgeKey",nativeQuery = true)
	List<MinMaxAgeLookUp> findByMinMaxAgeKey(@Param("minMaxAgeKey") Integer minMaxAgeKey);

}
