package com.amps.policy.config.repository;

import com.amps.policy.config.model.MedicalPolicyLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalPoliciesRepository extends JpaRepository<MedicalPolicyLookup, Integer> {}
