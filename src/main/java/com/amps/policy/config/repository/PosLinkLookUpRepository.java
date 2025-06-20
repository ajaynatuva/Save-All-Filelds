package com.amps.policy.config.repository;

import com.amps.policy.config.model.PosLinkLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosLinkLookUpRepository extends JpaRepository<PosLinkLookUp, Integer> {

}
