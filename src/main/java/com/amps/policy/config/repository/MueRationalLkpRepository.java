package com.amps.policy.config.repository;

import com.amps.policy.config.model.MueRationalLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MueRationalLkpRepository extends JpaRepository<MueRationalLookUp, Integer> {

}
