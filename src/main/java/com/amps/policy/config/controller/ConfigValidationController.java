package com.amps.policy.config.controller;

import com.amps.policy.config.dto.ConfigValidationDTO;
import com.amps.policy.config.service.ConfigValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/configvalidation")
@RestController
public class ConfigValidationController {

	@Autowired
	ConfigValidationService configValidationService;

	@PostMapping("/generateconfigreport")
	public void generateReport(@RequestBody ConfigValidationDTO configValidationDTO ){
		try {
			configValidationService.generateConfigValidationReport(configValidationDTO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
