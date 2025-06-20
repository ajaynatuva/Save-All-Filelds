package com.amps.policy.config.controller;

import com.amps.policy.config.dao.SearchPolicyDao;
import com.amps.policy.config.dto.PolicySearchDTO;
import com.amps.policy.config.dto.PolicySearchResultDTO;
import com.amps.policy.config.service.PolicySearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/searchPolicy")
@RestController
public class SearchPolicyController {

	@Autowired
	PolicySearchService policySearchService;

	@PostMapping(value = "/policyData", consumes = MediaType.APPLICATION_JSON_VALUE)
	private List<PolicySearchResultDTO> getData(@RequestBody Map<String, String> paramsMap) {
		ObjectMapper mapper = new ObjectMapper();
		PolicySearchDTO policyDto = mapper.convertValue(paramsMap, PolicySearchDTO.class);
			return policySearchService.serachCriteria(policyDto);
	}
}
