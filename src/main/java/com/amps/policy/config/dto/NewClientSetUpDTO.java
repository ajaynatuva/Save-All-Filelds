package com.amps.policy.config.dto;

import java.util.List;

import com.amps.policy.config.model.Changes;
import com.amps.policy.config.model.ClientAssignment;

public class NewClientSetUpDTO {

	List<ChangesDTO> changesData;
	List<ClientAssignmentDTO1> clientAssignmentData;

	public List<ChangesDTO> getChangesData() {
		return changesData;
	}

	public void setChangesData(List<ChangesDTO> changesData) {
		this.changesData = changesData;
	}

	public List<ClientAssignmentDTO1> getClientAssignmentData() {
		return clientAssignmentData;
	}

	public void setClientAssignmentData(List<ClientAssignmentDTO1> clientAssignmentData) {
		this.clientAssignmentData = clientAssignmentData;
	}
}
