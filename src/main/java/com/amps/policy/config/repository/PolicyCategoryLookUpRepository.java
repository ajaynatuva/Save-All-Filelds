package com.amps.policy.config.repository;

import com.amps.policy.config.model.PolicyCategoryLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyCategoryLookUpRepository extends JpaRepository<PolicyCategoryLookUp, Integer> {
	
	@Query(value = "SELECT * FROM policy.policy_category_lkp\r\n"
			+ "ORDER BY policy_category_key ASC ", nativeQuery = true)
	List<PolicyCategoryLookUp> sortCategoryById();
	
	@Query(value="SELECT * FROM policy.policy_category_lkp WHERE policy_category_lkp.policy_category_key=:policycategorykey",nativeQuery = true)
	List<PolicyCategoryLookUp> findBypolicycategorykey(@Param("policycategorykey") Integer policycategorykey);

}
