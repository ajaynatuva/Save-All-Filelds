package com.amps.policy.config.repository;

import com.amps.policy.config.model.CciRationaleLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PtpCciRationaleRepository extends JpaRepository<CciRationaleLookup, String> {

}