package com.amps.policy.config.repository;

import com.amps.policy.config.model.ModifierPayPercentageLkp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModifierPayPercentageLkpRepository extends JpaRepository<ModifierPayPercentageLkp, Integer>{

}
