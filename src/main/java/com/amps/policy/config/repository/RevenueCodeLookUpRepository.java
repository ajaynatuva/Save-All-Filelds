package com.amps.policy.config.repository;

import com.amps.policy.config.model.RevenueCodeLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RevenueCodeLookUpRepository extends JpaRepository<RevenueCodeLookUp, String> {
	
	@Query(value="SELECT * FROM source.rev_code_lkp WHERE rev_code_lkp.rev_code=:revCode",nativeQuery = true)
	List<RevenueCodeLookUp> findByRevCode(@Param("revCode") String revCode);

}