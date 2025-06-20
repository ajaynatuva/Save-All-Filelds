package com.amps.policy.config.repository;

import com.amps.policy.config.model.PolicyActionBillTypeLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyActionBillTypeLookUpRepository extends JpaRepository<PolicyActionBillTypeLookUp, Integer>{

}
