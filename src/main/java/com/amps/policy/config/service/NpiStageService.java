package com.amps.policy.config.service;

import org.springframework.web.multipart.MultipartFile;

public interface NpiStageService {

	String readFileAndSaveFile(MultipartFile file, String email);

}
