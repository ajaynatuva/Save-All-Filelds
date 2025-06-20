package com.amps.policy.config.repository;

import com.amps.policy.config.model.NpiLinkLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NpiLinkLookUpRepository extends JpaRepository<NpiLinkLookUp, Integer> {

}
