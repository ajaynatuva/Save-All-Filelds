package com.amps.policy.config.dao;

import com.amps.policy.config.dto.ClientGroupExclusionDTO;
import com.amps.policy.config.dto.ClientPolicyDTO;
import com.amps.policy.config.dto.ClientPolicyExclusionsDTO;
import com.amps.policy.config.dto.ClientgroupDTO;

import java.util.List;

public interface ClientGroupExclusionsDao {

	List<ClientPolicyDTO> getPolicyData();
	
	List<ClientgroupDTO> getExcluisonData();

	List<ClientPolicyExclusionsDTO> getPolicyExclusionData();

	List<ClientGroupExclusionDTO> postClientPolicyExclusionData(List<ClientGroupExclusionDTO> ClientGroupExclusionDTO);

    List<ClientGroupExclusionDTO> DeleteClientPolicyExclusionData(List<ClientGroupExclusionDTO> policyId);

}
