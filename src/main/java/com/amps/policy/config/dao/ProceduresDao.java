package com.amps.policy.config.dao;
import com.amps.policy.config.dto.ProceduresDTO;
import com.amps.policy.config.dto.ProceduresRowsDTO;
import com.amps.policy.config.dto.ProceduresSearchDTO;
import com.amps.policy.config.model.Procedures;
import java.util.List;

public interface ProceduresDao {

	public List<ProceduresDTO> findByPolicyId(int id);

	public void deleteByPolicyId(Integer policyId);

	public void saveAllStageData(Integer policyId);

	public List<Procedures> getAllData();

	public void PostProceduresData(List<ProceduresDTO> postProceduresData);

	public List<ProceduresDTO> findByProceduresData(int policyId);
	
    public ProceduresRowsDTO proceduresSearchCriteria(ProceduresSearchDTO proceduresSearchDTO); 
}
