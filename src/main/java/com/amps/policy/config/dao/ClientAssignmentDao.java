package com.amps.policy.config.dao;

import java.text.ParseException;
import java.util.List;

import com.amps.policy.config.dto.ClientAssignmentDTO;
import com.amps.policy.config.dto.ClientAssignmentDTO1;
import com.amps.policy.config.dto.copyClientDTO;
import com.amps.policy.config.model.ClientAssignment;

public interface ClientAssignmentDao {
	

	public void postClientAssignmentData1(List<ClientAssignmentDTO> clientAssignmentData);

	public void updateClientAssignmentData(ClientAssignment clientAssignmentData);

	public void updateClientAssignmentData1(ClientAssignmentDTO clientAssignmentData);

	public List<ClientAssignmentDTO> getActiveClientGroups();
	
	public List<ClientAssignmentDTO> getActiveClientGroupsNotHp();

	public void deleteClientAssignmentData(int id);
	
	public List<ClientAssignmentDTO1> getClientAssignmentData(int policyId);
	
	public List<copyClientDTO> getCopyClientAssignmentData();
	
	public List<copyClientDTO> getPolicyIds(List<copyClientDTO>clientGroupIds,List<copyClientDTO>clientGroupIds1) throws ParseException;

}
