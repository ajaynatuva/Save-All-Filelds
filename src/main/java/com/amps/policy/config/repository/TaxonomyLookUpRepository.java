package com.amps.policy.config.repository;

import com.amps.policy.config.model.TaxonomyLinkLookUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxonomyLookUpRepository extends JpaRepository<TaxonomyLinkLookUp, Integer> {

}
