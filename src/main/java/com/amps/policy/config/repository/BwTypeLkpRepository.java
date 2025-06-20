package com.amps.policy.config.repository;

import com.amps.policy.config.model.BwTypeLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BwTypeLkpRepository extends JpaRepository<BwTypeLookUp, Integer>{

}
