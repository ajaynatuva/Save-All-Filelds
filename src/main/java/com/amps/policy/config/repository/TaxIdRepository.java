package com.amps.policy.config.repository;

import com.amps.policy.config.model.TaxId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxIdRepository extends JpaRepository<TaxId, Integer> {
    @Query(value = "select * from policy.client_tin_exclusions where policy_id_fk =:policyId", nativeQuery = true)
    List<TaxId> findByPolicyId(int policyId);

    @Transactional
    @Modifying
    @Query(value = "update from policy.client_tin_exclusions set deleted_b = 1 where tax_id=:taxId and policy_id_fk =:policyId", nativeQuery = true)
    void deleteByTaxIdAndPolicyIdForOneRecord(Integer taxId, Integer policyId);

}
