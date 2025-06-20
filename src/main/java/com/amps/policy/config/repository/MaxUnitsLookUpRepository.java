package com.amps.policy.config.repository;

import com.amps.policy.config.model.MaxUnitsLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaxUnitsLookUpRepository extends JpaRepository<MaxUnitsLookUp, Integer> {

}
