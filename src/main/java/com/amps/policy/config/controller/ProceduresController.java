package com.amps.policy.config.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.amps.policy.config.dao.ProceduresDao;
import com.amps.policy.config.dto.ProceduresDTO;
import com.amps.policy.config.dto.ProceduresRowsDTO;
import com.amps.policy.config.dto.ProceduresSearchDTO;
import com.amps.policy.config.model.ClaimLinkLookUp;
import com.amps.policy.config.model.CptMaster;
import com.amps.policy.config.model.ModLookUp;
import com.amps.policy.config.model.PolicyActionCptLookUp;
import com.amps.policy.config.model.PosLookUp;
import com.amps.policy.config.model.RevenueCodeLookUp;
import com.amps.policy.config.service.LookUpService;
import com.amps.policy.config.service.PolicyService;
import com.amps.policy.config.service.StageService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/procedures")
@RestController
public class ProceduresController {

	@Autowired
	PolicyService policyService;

	@Autowired
	StageService stageService;

	@Autowired
	LookUpService lookUpService;

	@Autowired
	ProceduresDao proceduresDao;

	@PostMapping("/upload")
	public ResponseEntity<String> upload(@RequestParam("uploadfile") MultipartFile file,
			@RequestParam("email") String emailId) {
		String deltaPath = stageService.readFileAndSaveFile(file, emailId);
		if (deltaPath != null)
			return ResponseEntity.status(HttpStatus.OK).body(deltaPath);
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
	}

	@PostMapping("/stagetotarget/{policyId}")
	public ResponseEntity loadDataToProceduresTarget(@PathVariable Integer policyId)
			throws ClassNotFoundException, IOException, SQLException {
		stageService.loadDataToTarget(policyId);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping(value = "/cptmaster")
	public List<CptMaster> cpt() {
		return policyService.getCptData();
	}

	@GetMapping(value = "/Mod")
	public List<ModLookUp> mod() {
		return lookUpService.getModLkpData();
	}

	@GetMapping(value = "/Pos")
	public List<PosLookUp> pos() {
		return lookUpService.getPosLkpData();
	}

	@GetMapping(value = "/RevFrom")
	public List<RevenueCodeLookUp> rev() {
		return lookUpService.getRevCodeData();
	}

	@GetMapping(value = "/clmLinkLkp")
	public List<ClaimLinkLookUp> clmLinkLkp() {
		return policyService.getClmLinkLkpData();
	}

	@GetMapping(value = "/policyCptActionLkp")
	public List<PolicyActionCptLookUp> cptActionLkp() {
		return policyService.getCptActionLkpData();
	}

	@GetMapping("/procedureId/{id}")
	public List<ProceduresDTO> getPolicyId(@PathVariable int id) {
		return proceduresDao.findByProceduresData(id);
	}

	@PostMapping(value = "/searchProceduresData", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProceduresRowsDTO searchProceduresData(@RequestBody Map<String, String> paramsMap) {
		ObjectMapper mapper = new ObjectMapper();
		ProceduresSearchDTO proceduresSearchDto = mapper.convertValue(paramsMap, ProceduresSearchDTO.class);
		return  proceduresDao.proceduresSearchCriteria(proceduresSearchDto);
	}
}
