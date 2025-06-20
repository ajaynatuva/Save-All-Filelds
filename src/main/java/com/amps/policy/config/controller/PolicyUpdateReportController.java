package com.amps.policy.config.controller;

import com.amps.policy.config.dto.PolicyUpdateResultReportDTO;
import com.amps.policy.config.service.impl.PolicyUpdateReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/Report")
@RestController
public class PolicyUpdateReportController {

	@Autowired
	PolicyUpdateReportServiceImpl PolicyUpdateReportService;

	@PostMapping(value = "/uploadData", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<PolicyUpdateResultReportDTO> getFilteredPolicies(
			@RequestBody List<PolicyUpdateResultReportDTO> paramsMap) {
        return PolicyUpdateReportService.getPolicyUpdateReport(paramsMap);
	}
}
