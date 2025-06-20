package com.amps.policy.config.repository;

import com.amps.policy.config.model.TaxLinkLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxLinkLookUpRepository extends JpaRepository<TaxLinkLookUp, Integer> {

}
