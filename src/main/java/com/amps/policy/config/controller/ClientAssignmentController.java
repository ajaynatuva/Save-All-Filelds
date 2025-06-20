package com.amps.policy.config.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.amps.policy.config.dto.ChangesDTO;
import com.amps.policy.config.dto.ClientAssignmentDTO;
import com.amps.policy.config.dto.ClientAssignmentDTO1;
import com.amps.policy.config.dto.NewClientSetUpDTO;
import com.amps.policy.config.dto.copyClientDTO;
import com.amps.policy.config.service.ClientAssignmentService;
import com.amps.policy.config.service.PolicyService;

@RequestMapping(value = "/clientAssignment")
@RestController
public class ClientAssignmentController {

	@Autowired
	ClientAssignmentService clientAssignmentService;

	@Autowired
	PolicyService policyService;

	@GetMapping("/getClientAssignmentData/{policyId}")
	private List<ClientAssignmentDTO1> getClientAssignmentData1(@PathVariable Integer policyId) {
		return clientAssignmentService.getClientAssignmentTabData(policyId);
	}

	@GetMapping("/getActiveClientGroups")
	private List<ClientAssignmentDTO> getActiveClientGroups() {
		return clientAssignmentService.getActiveClientGroups();
	}

	@GetMapping("/getActiveClientGroupsNotHp")
	private List<ClientAssignmentDTO> getActiveClientGroupsNotHp() {
		return clientAssignmentService.getActiveClientGroupsNotHp();
	}

	@PostMapping("/postClientAssignment")
	private void postClientAssignment1(@RequestBody List<ClientAssignmentDTO1> clientAssignment) {
		clientAssignmentService.saveClientAssignment1(clientAssignment);
	}

	@PostMapping("/editClientAssignment")
	private void updateClientAssignment1(@RequestBody ClientAssignmentDTO1 clientAssignment) {
		clientAssignmentService.updateClientAssignment1(clientAssignment);
	}

	@PostMapping("/deleteClientAssignment/{id}")
	public void deleteClientAssignmentData(@PathVariable int id) {
		clientAssignmentService.deleteClientAssignmentData(id);
	}

	@PostMapping("/saveNewClientData")
	public void savenewClientSetUp(@RequestBody NewClientSetUpDTO dto) {
		List<ChangesDTO> changedData = dto.getChangesData();
		List<ClientAssignmentDTO1> clientData = dto.getClientAssignmentData();
		policyService.saveChangesListData(changedData);
		clientAssignmentService.saveClientAssignment1(clientData);
	}

	@GetMapping("/clientAssignmentData")
	public List<copyClientDTO> getCopyClientAssignmentData() {
		return clientAssignmentService.getCopyClientAssignmentData();
	}

	@PostMapping(value = "/clientAssignmentPolicyIds", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<copyClientDTO> getClientAssignmentPolicyIds(@RequestBody Map<String, List<copyClientDTO>> dtoMap)
			throws ParseException {
		List<copyClientDTO> dto = dtoMap.get("dto");
		List<copyClientDTO> dto1 = dtoMap.get("dto1");
		return clientAssignmentService.getClientAssignmentPolicyIds(dto, dto1);
	}
}
