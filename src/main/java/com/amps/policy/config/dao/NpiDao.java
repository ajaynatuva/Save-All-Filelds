package com.amps.policy.config.dao;

import com.amps.policy.config.dto.NpiDTO;
import com.amps.policy.config.model.Npi;

import java.util.List;

public interface NpiDao {


    void deleteClientNpiExclusionsByPolicyId(List<Npi> npiList);

    List<Npi> getNpiGroupedDetails(Integer policyId);

    void saveNpiDataToSource(List<Npi> npiList);
    
    // file upload import methods
    void deleteStageData(Integer policyId);
    
    void deleteDeltaData(Integer policyId);
    
    void loadDataToStage(List<NpiDTO> npiDTO);
    
    void saveAllStageDataToTarget(Integer policyId);
    
    //Delta Generation
    boolean generateDeltaReport(Integer policyId, String sharePointDeltaPath);
    
    boolean generateExceptionReport(Integer policyId,String sharePointDeltaPath);

    List<Npi> getAllDeleteActionNPIData(Integer policyId);
}

