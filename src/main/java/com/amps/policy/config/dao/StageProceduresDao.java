package com.amps.policy.config.dao;

import com.amps.policy.config.dto.ProceduresDTO;
import com.amps.policy.config.model.StageProcedures;

import java.util.List;

public interface StageProceduresDao {

	boolean generateDeltaReport(Integer policyId, String sharePointDeltaPath);
	
	void deleteAllData();
	
	void deleteByPolicyId(Integer policyId);

	List<StageProcedures> getAllData();
	
	void saveData(List<ProceduresDTO> proceduresDTO);

}
