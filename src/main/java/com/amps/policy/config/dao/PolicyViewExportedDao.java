package com.amps.policy.config.dao;

import com.amps.policy.config.dto.PolicyViewExportDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PolicyViewExportedDao {
	byte[] getExportPolicies(List<PolicyViewExportDTO> paramsMap, String type);
}
