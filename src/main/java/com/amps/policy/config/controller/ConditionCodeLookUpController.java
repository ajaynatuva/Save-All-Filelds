package com.amps.policy.config.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.amps.policy.config.model.ConditionCodeActionLkp;
import com.amps.policy.config.model.PolicyConditionCode;
import com.amps.policy.config.service.PolicyConditionCodeService;

@RequestMapping("/condition")
@RestController
public class ConditionCodeLookUpController {

	@Autowired
	PolicyConditionCodeService policyConditionCodeService;

	@GetMapping("/actionLookUp")
	List<ConditionCodeActionLkp> GetConditionLkpData() {
		return policyConditionCodeService.getConditionLkpData();
	}

	@GetMapping("/conditionTypeData/{policyId}")
	public List<PolicyConditionCode> getPolicyConditionDataById(@PathVariable int policyId) {
		return policyConditionCodeService.getPolicyConditionDataById(policyId);
	}

	@PostMapping(value = "/postConditionType", consumes = MediaType.APPLICATION_JSON_VALUE)
	private void postConditionTypeData(@RequestBody List<PolicyConditionCode> policyConditionTypes) {
		policyConditionCodeService.postConditionTypeData(policyConditionTypes);
	}

	@PostMapping(value = "/deleteData/{key}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deletePolicyConditionTypeData(@PathVariable int key) {
		policyConditionCodeService.deletePolicyConditionTypeData(key);
	}
}
