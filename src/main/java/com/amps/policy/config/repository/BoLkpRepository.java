package com.amps.policy.config.repository;

import com.amps.policy.config.model.BoTypeLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoLkpRepository extends JpaRepository<BoTypeLookUp, Integer>{

}
