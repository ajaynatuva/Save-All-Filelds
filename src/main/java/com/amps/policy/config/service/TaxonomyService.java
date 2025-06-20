package com.amps.policy.config.service;

import com.amps.policy.config.model.Taxonomy;

import java.util.List;

public interface TaxonomyService {
    List<Taxonomy> getTaxonomyOfPolicy(int policyId);

    void saveTaxonomy(Taxonomy taxonomy);

    void deleteTaxonomyById(Integer taxonomyKey);

    void deleteByTaxonomy(String taxonomyCode,Integer policyId);

    void uploadTaxonomy(List<Taxonomy> taxonomy);

    List<Taxonomy> getTaxonomyByPolicyId(Integer policy_Id);

    void postTaxonomyData(List<Taxonomy> policyTaxonomy);

    void disableTaxonomyByKey(Integer taxonomyKey);
}
