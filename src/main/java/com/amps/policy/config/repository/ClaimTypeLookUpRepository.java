package com.amps.policy.config.repository;

import com.amps.policy.config.model.ClaimTypeLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimTypeLookUpRepository extends JpaRepository<ClaimTypeLookUp, Integer> {
}
