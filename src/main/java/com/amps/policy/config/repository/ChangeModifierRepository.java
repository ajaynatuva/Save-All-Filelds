package com.amps.policy.config.repository;

import com.amps.policy.config.model.ChangeModifierLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeModifierRepository extends JpaRepository<ChangeModifierLookUp, Integer>{

}
