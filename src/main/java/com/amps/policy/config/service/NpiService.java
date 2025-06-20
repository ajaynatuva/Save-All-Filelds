package com.amps.policy.config.service;

import java.util.List;

import com.amps.policy.config.dto.ChangesDTO;
import com.amps.policy.config.model.Npi;

public interface NpiService {

    void saveNpiData(List<Npi> npiList);

    void deleteByNpi(List<Npi> npiList);

    List<Npi> getNpiGroupedDetails(Integer policyId);

    void saveNewNpiChanges(ChangesDTO changesDTO);

    List<Npi> loadDataToTarget(Integer policyId);
    
    List<Npi> getNPIPolicyIdData(int policyId);
}
