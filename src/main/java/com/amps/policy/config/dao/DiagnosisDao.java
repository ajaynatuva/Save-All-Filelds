package com.amps.policy.config.dao;

import com.amps.policy.config.dto.DiagnosisDTO;
import com.amps.policy.config.model.Diagnosis;

import java.util.List;

public interface DiagnosisDao {
	public List<DiagnosisDTO> findByPolicyId(int id);

	// using for saving file data
	void saveData(List<DiagnosisDTO> DiagnosisDTO);

	void deleteData(int id);

	void updateData(Diagnosis diagnosis);

	// using for saving list data
	void saveDiagsData(List<Diagnosis> diagnosis);

	void deleteDxData(int id);
	
	public List<DiagnosisDTO>getDxExportById(int id);

	void updateDiagHeaders(Diagnosis diagnosis);
}
