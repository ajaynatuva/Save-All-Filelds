package com.amps.policy.config.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.amps.policy.config.dto.ClientGroupExclusionDTO;
import com.amps.policy.config.dto.ClientPolicyDTO;
import com.amps.policy.config.dto.ClientPolicyExclusionsDTO;
import com.amps.policy.config.dto.ClientgroupDTO;
import com.amps.policy.config.service.ClientPolicyExclusionService;

@RequestMapping(value = "/clientPolicyExclusion")
@RestController
public class ClientPolicyExclusionsController {

	@Autowired
	ClientPolicyExclusionService clientPolicyExclusionService;

	@GetMapping("/policyExclusion")
	private List<ClientPolicyDTO> getPolicyData() {
		return clientPolicyExclusionService.getPolicyData();
	}

	@GetMapping("/clientExclusion")
	private List<ClientgroupDTO> getClientExclusionData() {
		return clientPolicyExclusionService.getExcluisonData();
	}

	@GetMapping("/clientPolicyData")
	private List<ClientPolicyExclusionsDTO> getClientPolicyData() {
		return clientPolicyExclusionService.getPolicyExclusionData();
	}

	@PostMapping(value = "/saveExclusion", consumes = MediaType.APPLICATION_JSON_VALUE)
	private List<ClientGroupExclusionDTO> postData(@RequestBody List<ClientGroupExclusionDTO> clientGroupExclusionDTO) {
		clientPolicyExclusionService.postClientPolicyExclusionData(clientGroupExclusionDTO);
		return clientGroupExclusionDTO;
	}

	@PostMapping(value = "/deleteExclusion", consumes = MediaType.APPLICATION_JSON_VALUE)
	private List<ClientGroupExclusionDTO> DeleteData(@RequestBody List<ClientGroupExclusionDTO> deleteDTO) {
		clientPolicyExclusionService.DeleteClientPolicyExclusionData(deleteDTO);
		return deleteDTO;
	}
}
