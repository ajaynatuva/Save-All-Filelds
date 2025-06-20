package com.amps.policy.config.repository;

import com.amps.policy.config.model.ClaimTypeLinkLkp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimTypeLinkLkpRepository extends JpaRepository<ClaimTypeLinkLkp, Integer> {

}
