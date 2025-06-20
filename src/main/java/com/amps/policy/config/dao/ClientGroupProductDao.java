package com.amps.policy.config.dao;

import com.amps.policy.config.dto.ClientAssignmentDTO;

import java.util.List;

public interface ClientGroupProductDao {

    public List<ClientAssignmentDTO> getClientGroupProductData();
    public List<ClientAssignmentDTO> getClientAssignmentData();
}
