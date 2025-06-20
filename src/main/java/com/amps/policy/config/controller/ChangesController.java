package com.amps.policy.config.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.amps.policy.config.dto.ChangesDTO;
import com.amps.policy.config.model.Changes;
import com.amps.policy.config.model.Policy;
import com.amps.policy.config.service.PolicyService;

@RequestMapping("/Changes")
@RestController
public class ChangesController {

	@Autowired
	PolicyService policyService;

	@GetMapping("/changes")
	private List<Changes> getchangesDetails() {
		return policyService.getChangesData();
	}

	@PostMapping(value = "/savechanges", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Changes saveChanges(@RequestBody ChangesDTO changesDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Changes changes = modelMapper.map(changesDTO, Changes.class);
		Policy policy = policyService.findByPolicyId(changesDTO.getPolicyId());
		changes.setPolicyId(policy);
		changes.setUpdatedOn(changes.getUpdatedOn() == null ?new Date():changes.getUpdatedOn());
		return policyService.saveChanges(changes);
	}

	@PostMapping(value = "/saveCustomChanges", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer saveCustomChanges(@RequestBody ChangesDTO changesDTO) {
		Policy policy = policyService.findByPolicyId(changesDTO.getPolicyId());
		List<ChangesDTO> changesData = policyService.getLatestChangesRecord(policy.getClonedPolicyId());
		if (!changesData.isEmpty()) {
			changesData = changesData.stream()
					.peek(chan -> {
						chan.setPolicyId(policy.getPolicyId());
						chan.setIsOpenb(chan.getIsOpenb() == 1 ? 0 : chan.getIsOpenb());
					})
					.collect(Collectors.toList());
			changesData.add(changesDTO);
			policyService.saveChangesListData(changesData);
		}
		return changesDTO.getPolicyId();
	}

	@GetMapping("/GetChangesById/{id}")
	private List<Changes> getPolicyId(@PathVariable int id) {
		return policyService.getChangesById(id);
	}

	@PostMapping("/updateChanges")
	private Changes updateChanges(@RequestBody ChangesDTO changesDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Changes changes = modelMapper.map(changesDTO, Changes.class);
		Policy policy = policyService.findByPolicyId(changesDTO.getPolicyId());
		changes.setPolicyId(policy);
		changes.setUpdatedOn(new Date());
		return policyService.saveChanges(changes);
	}

	// Procs tab
	@GetMapping("/GetChanges/{id}")
	private List<Changes> getChangesByPolicyId(@PathVariable int id) {
		return policyService.getChangesByIdOpenB(id);
	}
}
