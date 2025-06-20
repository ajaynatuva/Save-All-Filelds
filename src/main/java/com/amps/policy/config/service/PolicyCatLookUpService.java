package com.amps.policy.config.service;

import org.springframework.web.multipart.MultipartFile;

public interface PolicyCatLookUpService {
	void saveToDatabase(MultipartFile file);
}
