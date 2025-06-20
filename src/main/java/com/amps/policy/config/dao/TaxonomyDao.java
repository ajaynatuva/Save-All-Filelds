package com.amps.policy.config.dao;

import com.amps.policy.config.dto.TaxonomyDTO;
import com.amps.policy.config.model.Taxonomy;

import java.util.List;

public interface TaxonomyDao {
    void saveData(List<TaxonomyDTO> taxonomyDTO);

    //    void deleteAllData();
    void deleteStageData(Integer policyId);
    void deleteDeltaData(Integer policyId);
    boolean generateDeltaReport(Integer policyId, String sharePointDeltaPath);

//    void deleteByPolicyId(Integer policyId);

    List<Taxonomy> getAllDeleteActionTaxonomyData(Integer policyId);

    void deleteTaxonomyData(List<Taxonomy> taxonomyList);

    void saveAllStageData(Integer policyId);

    boolean generateExceptionReport(Integer policyId,String sharePointDeltaPath);
}
