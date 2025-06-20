package com.amps.policy.config.service;

import org.springframework.web.multipart.MultipartFile;

public interface TaxIdStageService {

    String readFileAndSaveFile(MultipartFile file, String email);

}
