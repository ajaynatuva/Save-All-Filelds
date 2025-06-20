package com.amps.policy.config.repository;

import com.amps.policy.config.model.MaxUnitsTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaxUnitsTypeRepository extends JpaRepository<MaxUnitsTypes, Integer>{

}
