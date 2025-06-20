package com.amps.policy.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amps.policy.config.dao.ClientGroupExclusionsDao;
import com.amps.policy.config.dto.ClientGroupExclusionDTO;
import com.amps.policy.config.dto.ClientPolicyDTO;
import com.amps.policy.config.dto.ClientPolicyExclusionsDTO;
import com.amps.policy.config.dto.ClientgroupDTO;
import com.amps.policy.config.service.ClientPolicyExclusionService;

@Component
public class ClientPolicyExclusionServiceImpl implements ClientPolicyExclusionService{

	
	@Autowired
    ClientGroupExclusionsDao clientgroupExclusionsDao;

	
	@Override
	public List<ClientPolicyDTO> getPolicyData() {
		// TODO Auto-generated method stub
		return clientgroupExclusionsDao.getPolicyData();
	}

	@Override
	public List<ClientgroupDTO> getExcluisonData() {
		// TODO Auto-generated method stub
		return clientgroupExclusionsDao.getExcluisonData();
	}

	@Override
	public List<ClientPolicyExclusionsDTO> getPolicyExclusionData() {
		// TODO Auto-generated method stub
		return clientgroupExclusionsDao.getPolicyExclusionData();
	}

	@Override
	public List<ClientGroupExclusionDTO> postClientPolicyExclusionData(
			List<ClientGroupExclusionDTO> clientGroupExclusionDTO) {
		// TODO Auto-generated method stub
		return clientgroupExclusionsDao.postClientPolicyExclusionData(clientGroupExclusionDTO);
	}

	@Override
	public List<ClientGroupExclusionDTO> DeleteClientPolicyExclusionData(List<ClientGroupExclusionDTO> deleteDTO) {
		// TODO Auto-generated method stub
		return clientgroupExclusionsDao.DeleteClientPolicyExclusionData(deleteDTO);
	}

}
