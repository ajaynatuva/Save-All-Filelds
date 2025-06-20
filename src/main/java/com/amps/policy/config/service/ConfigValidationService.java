package com.amps.policy.config.service;

import com.amps.policy.config.dto.ConfigValidationDTO;
import com.amps.policy.config.dto.ConfigValidationReportDTO;

import java.io.IOException;

public interface ConfigValidationService {
	public void generateConfigValidationReport(ConfigValidationDTO configValidationDTO) throws IOException;
}
