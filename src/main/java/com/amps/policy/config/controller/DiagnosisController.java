package com.amps.policy.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.amps.policy.config.dto.DiagnosisDTO;
import com.amps.policy.config.model.Diagnosis;
import com.amps.policy.config.model.PolicyActionDiagnosisLookUp;
import com.amps.policy.config.service.DiagnosisService;

@RequestMapping(value = "/Diagnosis")
@RestController
public class DiagnosisController {

	@Autowired
	DiagnosisService diagnosisService;

	@GetMapping("/Totaldata/{policy_id}")
	public List<Diagnosis> find(@PathVariable int policy_id) {
		return diagnosisService.getDiagnosisData(policy_id);
	}

	@PostMapping("/postDiagnosis")
	private void postDiagnosisData(@RequestBody Diagnosis Diagnosis) {
		diagnosisService.saveDiagnosisData(Diagnosis);
	}

	@PostMapping(value = "/deleteDiagnosis/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteData(@PathVariable int id) {
		diagnosisService.deleteDiagnosisData(id);
	}


	@PostMapping(value = "/updateDiagnosis", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateData(@RequestBody Diagnosis Diagnosis) {
		diagnosisService.updateDiagnosisData(Diagnosis);
	}

	@PostMapping(value = "/updateDiagHeaders", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer updateDiagHeaders(@RequestBody Diagnosis diagnosis) {
		diagnosisService.updateDiagHeaders(diagnosis);
		return diagnosis.getPolicyId();
	}

	@GetMapping("/getActionName")
	private List<PolicyActionDiagnosisLookUp> getQuarterNames() {
		return diagnosisService.getPolicyActionDiagnosisLkpData();
	}

	@GetMapping("/dxExportId/{id}")
	public List<DiagnosisDTO> getPolicyExportId(@PathVariable int id) {
		return diagnosisService.ExportDiagnosisData(id);
	}

	@PostMapping("/upload")
	public ResponseEntity<String> upload(@RequestParam("uploadfile") MultipartFile file, String email) {
		String deltaPath = diagnosisService.readFileAndSaveFile(file, email);
		return ResponseEntity.status(HttpStatus.OK).body(deltaPath);
	}
}
