package com.amps.policy.config.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amps.policy.config.model.PolicyBillType;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyBillTypeRepository extends JpaRepository<PolicyBillType, Integer>  {
	
	@Query(value = "select * from policy.policy_bill_types where policy_id = :policy_id", nativeQuery = true)
	List<PolicyBillType> getBillTypeData(@Param("policy_id") int policy_id);
}
