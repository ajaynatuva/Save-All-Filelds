package com.amps.policy.config.repository;

import com.amps.policy.config.model.ReasonCodeLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReasonCodeLookUpRepository extends JpaRepository<ReasonCodeLookUp, Integer> {
	
	@Query(value="SELECT * FROM policy.reasoncode_lkp WHERE reasoncode_lkp.reason_code=:reasoncode",nativeQuery = true)
	List<ReasonCodeLookUp> findByreasoncode(@Param("reasoncode") String reasoncode);

}
