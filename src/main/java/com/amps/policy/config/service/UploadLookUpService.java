package com.amps.policy.config.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadLookUpService {
	
	void saveToDatabase(MultipartFile file,String className);

}
