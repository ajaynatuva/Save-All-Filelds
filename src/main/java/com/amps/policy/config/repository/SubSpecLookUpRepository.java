package com.amps.policy.config.repository;

import com.amps.policy.config.model.SubSpecMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubSpecLookUpRepository extends JpaRepository<SubSpecMap, Integer> {
	@Query(value="SELECT * FROM policy.subspec_map WHERE subspec_map.subspec_code=:subSpecCode",nativeQuery = true)
	List<SubSpecMap> findBySubSpecCode(@Param("subSpecCode") String subSpecCode);
}
