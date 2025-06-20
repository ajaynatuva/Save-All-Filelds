package com.amps.policy.config.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.amps.policy.config.dto.ChangesDTO;
import com.amps.policy.config.model.Npi;
import com.amps.policy.config.service.NpiService;
import com.amps.policy.config.service.NpiStageService;

@RestController
@RequestMapping("/npi")
public class NpiController {

	@Autowired
	NpiService npiService;

	@Autowired
	NpiStageService npiStageService;

	@GetMapping(value = "/getNpiByPolicyId/{policyId}")
	public List<Npi> getNpiListData(@PathVariable Integer policyId) {
		return npiService.getNpiGroupedDetails(policyId);
	}

	@PostMapping(value = "/deleteNpiById")
	public List<Npi> deleteByNpi(@RequestBody List<Npi> npiList) {
		npiService.deleteByNpi(npiList);
		return npiService.getNpiGroupedDetails(npiList.getFirst().getPolicyIdFk());
	}

	@PostMapping(value = "/saveNpiDetails")
	public List<Npi> saveNpiDetails(@RequestBody List<Npi> npiList) {
		npiService.saveNpiData(npiList);
		return npiService.getNpiGroupedDetails(npiList.getFirst().getPolicyIdFk());
	}

	@PostMapping("/uploadNpiDataToStage")
	public ResponseEntity<String> uploadNpiData(@RequestParam("uploadfile") MultipartFile file,
			@RequestParam("email") String emailId) {

		String deltaPath = npiStageService.readFileAndSaveFile(file, emailId);
		if (deltaPath != null)
			return ResponseEntity.status(HttpStatus.OK).body(deltaPath);
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
	}

	@PostMapping("/saveNpiDataToTarget/{policyId}")
	public ResponseEntity <List<Npi>> loadNpiDataToTarget(@PathVariable Integer policyId, @RequestBody ChangesDTO changesNpiData)  throws ClassNotFoundException, IOException, SQLException{

		npiService.saveNewNpiChanges(changesNpiData);

        List<Npi> data =  npiService.loadDataToTarget(policyId);

		if (!data.isEmpty()) {
			return ResponseEntity.ok(data);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.emptyList());
		}
	}
}
