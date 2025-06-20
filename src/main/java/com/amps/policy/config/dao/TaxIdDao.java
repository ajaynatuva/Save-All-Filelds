package com.amps.policy.config.dao;

import com.amps.policy.config.dto.TaxIdDTO;
import com.amps.policy.config.model.TaxId;

import java.util.List;

public interface TaxIdDao {

    void saveData(List<TaxIdDTO> taxIdDTO);
    void deleteStageData(Integer policyId);
    void deleteDeltaData(Integer policyId);
    List<TaxId> getAllDeleteActionCTEData(Integer policyId);
    boolean generateDeltaReport(Integer policyId, String sharePointDeltaPath);

    void deleteClientTinExclusionsByPolicyId(List<TaxId> taxIdList);
    void saveAllStageDataToTarget(Integer policyId);
    boolean generateExceptionReport(Integer policyId,String sharePointDeltaPath);
    List<TaxId> getTaxIdGroupedDetails(Integer policyId);
    void saveTaxIdDataToSource(List<TaxId>taxIdList);
}
