package com.amps.policy.config.service;

import com.amps.policy.config.dto.DiagnosisDTO;
import com.amps.policy.config.model.Diagnosis;
import com.amps.policy.config.model.PolicyActionDiagnosisLookUp;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DiagnosisService {

	String readFileAndSaveFile(MultipartFile file, String email);

	Integer saveToDatabase(MultipartFile file);

	String uploadToSharepoint(MultipartFile file, Integer policyId);

	void deleteDiagnosisData(int id);

	void updateDiagnosisData(Diagnosis diagnosis);

	List<Diagnosis> getDiagnosisData(int policy_id);

	void saveDiagnosisData(Diagnosis diagnosis);

	List<DiagnosisDTO> ExportDiagnosisData(int id);
	
	List<PolicyActionDiagnosisLookUp>getPolicyActionDiagnosisLkpData();

	void saveDiagnosisListData(List<Diagnosis> diagnosis);

	void updateDiagHeaders(Diagnosis diagnosis);
}
