package com.amps.policy.config.repository;

import com.amps.policy.config.model.PolicyBillTypeLinkLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTypeLinkLkpRepository extends JpaRepository<PolicyBillTypeLinkLookUp, Integer>{

}
