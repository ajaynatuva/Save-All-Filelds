package com.amps.policy.config.repository;

import com.amps.policy.config.model.Npi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NpiRepository extends JpaRepository<Npi, Integer> {
    @Query(value = "select * from policy.client_npi_exclusions where policy_id_fk =:policyId", nativeQuery = true)
    List<Npi> findByPolicyId(int policyId);
}
