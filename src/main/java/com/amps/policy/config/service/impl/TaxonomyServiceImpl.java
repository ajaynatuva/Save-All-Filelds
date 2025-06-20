package com.amps.policy.config.service.impl;

import com.amps.policy.config.model.Taxonomy;
import com.amps.policy.config.repository.TaxonomyRepository;
import com.amps.policy.config.service.TaxonomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxonomyServiceImpl implements TaxonomyService {

    @Autowired
    TaxonomyRepository taxonomyRepository;

    @Autowired
    NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

    @Override
    public List<Taxonomy> getTaxonomyOfPolicy(int policyId) {
        return taxonomyRepository.findByPolicyId(policyId);
    }

    @Override
    public void saveTaxonomy(Taxonomy taxonomy) {
        taxonomyRepository.saveTaxonomy(taxonomy.getPolicyId(), taxonomy.getClientGroupId(),taxonomy.getTaxonomyCode(),taxonomy.getSpecCode(), taxonomy.getSubSpecCode(), taxonomy.getSubSpecDesc(),taxonomy.getFunction(),taxonomy.getDeletedB());
    }


    @Override
    public void deleteTaxonomyById(Integer taxonomyKey) {
        taxonomyRepository.deleteById(taxonomyKey);
    }

    @Override
    public void disableTaxonomyByKey(Integer taxonomyKey) {
        // TODO Auto-generated method stub
        taxonomyRepository.disableTaxonomy(taxonomyKey);

    }

    @Override
    public void deleteByTaxonomy(String taxonomyCode,Integer policyId) {
        taxonomyRepository.deleteByTaxonomy(taxonomyCode,policyId);
    }

    @Override
    public void uploadTaxonomy(List<Taxonomy> taxonomyList){
        taxonomyRepository.deleteByPolicyId(taxonomyList.get(0).getPolicyId());
        taxonomyList.forEach(taxonomy -> taxonomyRepository.save((taxonomy)));
    }

    @Override
    public List<Taxonomy> getTaxonomyByPolicyId(Integer policy_Id) {
        return taxonomyRepository.getTaxonomyByPolicyId(policy_Id);
    }

    @Override
    public void postTaxonomyData(List<Taxonomy> policyTaxonomy) {
        // TODO Auto-generated method stub
        String Query = """
                INSERT INTO policy.taxonomy(
                policy_id, client_group_id, taxonomy_code, spec_code, subspec_code, subspec_desc, function,created_date,updated_date)
                VALUES (:policyId, :clientGroupId, :taxonomyCode, :specCode, :subSpecCode, :subSpecDesc, :function,:createdDate,:updatedDate)
                """;
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(policyTaxonomy.toArray());
        ipuNamedParameterJdbcTemplate.batchUpdate(Query, params);
    }
}
