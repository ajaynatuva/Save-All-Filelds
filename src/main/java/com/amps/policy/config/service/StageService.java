package com.amps.policy.config.service;

import org.springframework.web.multipart.MultipartFile;

public interface StageService {

	String readFileAndSaveFile(MultipartFile file, String email);

	String uploadToSharePoint(MultipartFile file, Integer policyId);

	Integer saveToDatabase(MultipartFile file);

	void loadDataToTarget(Integer policyId);

	String getDeltaReport(Integer policyId, String sourcePath,String email);

}
