package com.amps.policy.config.repository;

import com.amps.policy.config.model.CptMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CptMasterRepository extends JpaRepository<CptMaster, Integer> {

}
