package com.amps.policy.config.repository;

import com.amps.policy.config.model.PosLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosLookUpRepository extends JpaRepository<PosLookUp, Integer> {

	@Query(value = "SELECT * FROM source.pos_lkp WHERE pos_lkp.pos_code=:poscode", nativeQuery = true)
	List<PosLookUp> findByposCode(@Param("poscode") String poscode);

}
