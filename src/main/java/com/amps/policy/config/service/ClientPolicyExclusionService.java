package com.amps.policy.config.service;

import java.util.List;

import com.amps.policy.config.dto.ClientGroupExclusionDTO;
import com.amps.policy.config.dto.ClientPolicyDTO;
import com.amps.policy.config.dto.ClientPolicyExclusionsDTO;
import com.amps.policy.config.dto.ClientgroupDTO;

public interface ClientPolicyExclusionService {

	List<ClientPolicyDTO> getPolicyData();

	List<ClientgroupDTO> getExcluisonData();

	List<ClientPolicyExclusionsDTO> getPolicyExclusionData();

	List<ClientGroupExclusionDTO> postClientPolicyExclusionData(List<ClientGroupExclusionDTO> clientGroupExclusionDTO);

	List<ClientGroupExclusionDTO> DeleteClientPolicyExclusionData(List<ClientGroupExclusionDTO> deleteDTO);
}
