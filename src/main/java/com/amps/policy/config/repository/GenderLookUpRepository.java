package com.amps.policy.config.repository;

import com.amps.policy.config.model.GenderLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderLookUpRepository extends JpaRepository<GenderLookUp, Integer> {

}
