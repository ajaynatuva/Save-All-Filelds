package com.amps.policy.config.repository;

import com.amps.policy.config.model.Taxonomy;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxonomyRepository extends JpaRepository<Taxonomy,Integer> {

    @Query(value = "select * from policy.taxonomy where policy_id=:policyId order by deleted_b asc, function desc",nativeQuery = true)
    List<Taxonomy> findByPolicyId(int policyId);

    @Query(value = "delete from policy.taxonomy where policy_id=:policyId",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByPolicyId(int policyId);

    @Query(value = "select * from policy.taxonomy where policy_id =:policy_Id", nativeQuery = true)
    List<Taxonomy> getTaxonomyByPolicyId(@Param("policy_Id") Integer policy_Id);

    @Query(value = "delete from policy.taxonomy where taxonomy_code=:taxonomyCode and policy_id =:policyId",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByTaxonomy(String taxonomyCode,Integer policyId);

    @Query(value = "update policy.taxonomy set deleted_b = 1,updated_date = now() where taxonomy_key =:taxonomykey",nativeQuery = true)
    @Modifying
    @Transactional
    void disableTaxonomy(Integer taxonomykey);

    @Query(value = "insert into policy.taxonomy(policy_id,client_group_id,taxonomy_code,spec_code,subspec_code,subspec_desc,function,deleted_b,created_date,updated_date) values(:policyId,:clientGroupId,:taxonomyCode,:specCode,:subSpecCode,:subSpecDesc,:function,:DeletedB,now(),now()) ",nativeQuery = true)
    @Modifying
    @Transactional
    void saveTaxonomy(Integer policyId,Integer clientGroupId,String taxonomyCode,String specCode,String subSpecCode,String subSpecDesc,Integer function,Integer DeletedB);


}
