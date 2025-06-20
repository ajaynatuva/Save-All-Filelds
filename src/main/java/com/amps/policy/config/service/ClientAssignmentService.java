package com.amps.policy.config.service;

import java.text.ParseException;
import java.util.List;

import com.amps.policy.config.dto.ClientAssignmentDTO;
import com.amps.policy.config.dto.ClientAssignmentDTO1;
import com.amps.policy.config.dto.copyClientDTO;
import com.amps.policy.config.model.ClientAssignment;

public interface ClientAssignmentService {

    void deleteClientAssignmentData(int id);

    List<ClientAssignmentDTO1> getClientAssignmentTabData(int id);
    
    List<copyClientDTO>getCopyClientAssignmentData();

    void saveClientAssignment1(List<ClientAssignmentDTO1> clientAssignment);

    void updateClientAssignment1(ClientAssignmentDTO1 clientAssignment);
    
    List<copyClientDTO>getClientAssignmentPolicyIds(List<copyClientDTO>clientGroupIds,List<copyClientDTO>clientGroupIds1) throws ParseException;

    List<ClientAssignmentDTO> getActiveClientGroups();

    List<ClientAssignmentDTO> getActiveClientGroupsNotHp();

}
