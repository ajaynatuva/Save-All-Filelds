package com.amps.policy.config.repository;

import com.amps.policy.config.model.ClaimLinkLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimLinkLkpRepository extends JpaRepository<ClaimLinkLookUp, Integer> {

}
