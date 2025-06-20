package com.amps.policy.config.repository;

import com.amps.policy.config.model.PolicyActionCptLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyActionCptLookUpRepository extends JpaRepository<PolicyActionCptLookUp, Integer> {

	PolicyActionCptLookUp findByPolicyCptActionKey(Integer policyCptActionKey);

}
