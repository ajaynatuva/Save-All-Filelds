package com.amps.policy.config.repository;

import com.amps.policy.config.model.SubPolicies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubPoliciesRepository extends JpaRepository<SubPolicies, Integer> {

}
