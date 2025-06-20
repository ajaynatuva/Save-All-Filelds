package com.amps.policy.config.service;

import com.amps.policy.config.dto.PolicyUpdateResultReportDTO;
import com.amps.policy.config.dto.PolicyUploadDTO;

import java.util.List;

public interface PolicyUpdateReportService {

	List<PolicyUpdateResultReportDTO> getPolicyUpdateReport(List<PolicyUpdateResultReportDTO> cptCode);
	
	List<PolicyUploadDTO>getSameOrSimData();
}
