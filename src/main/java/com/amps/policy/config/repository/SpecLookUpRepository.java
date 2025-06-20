package com.amps.policy.config.repository;

import com.amps.policy.config.model.SpecLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecLookUpRepository extends JpaRepository<SpecLookUp, Integer> {

	@Query(value="SELECT * FROM policy.spec_lkp WHERE spec_lkp.spec_code=:specCode",nativeQuery = true)
	List<SpecLookUp> findBySpecCode(@Param("specCode") String specCode);
	
	
	@Query(value="update policy.spec_lkp set spec_lkp.spec_desc=:specDesc, spec_lkp.spec_code =:specCode where spec_lkp.spec_code=:specCode",nativeQuery = true)
	List<SpecLookUp> updateBySpecCode(@Param("specCode") String specCode);


}

