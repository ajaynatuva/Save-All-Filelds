package com.amps.policy.config.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Objects;

import com.amps.policy.config.dao.PolicyViewDao;
import org.apache.commons.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.amps.policy.config.dto.CategoryDTO;
import com.amps.policy.config.dto.PolicyViewDTO;
import com.amps.policy.config.dto.PolicyViewExportDTO;
import com.amps.policy.config.service.PolicyViewService;
import com.amps.policy.config.util.SharePointUtil;

@RequestMapping(value = "/policyView")
@RestController
public class PolicyViewController {

    @Autowired
    PolicyViewService policyViewService;

    @Autowired
    PolicyViewDao policyViewDao;

    @Autowired
    SharePointUtil sharePointUtil;

    @Value("${sharepoint.app.location}")
    String sharePointAppLocation;

    @GetMapping("/categoryData")
    public List<CategoryDTO> getCategoryData() {
        return policyViewService.getCategoryData();
    }


    @PostMapping(value = "/filterSubPol", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PolicyViewDTO> getSubPolicyByCat(@RequestBody List<PolicyViewDTO> paramsMap) {
        return policyViewService.getSubPolicyByCat(paramsMap);
    }

    @PostMapping(value = "/filterReason", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PolicyViewDTO> getFilterdReasonPolicies(@RequestBody List<PolicyViewDTO> paramsMap) {
        return policyViewService.getFilterdReasonPolicies(paramsMap);
    }

    @PostMapping(value = "/filterPolicies", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PolicyViewDTO> getFilterdPolicies(@RequestBody List<PolicyViewDTO> paramsMap) {
        return policyViewService.getFilterdPolicies(paramsMap);
    }

    @PostMapping(value = "/policyViewData", consumes = MediaType.APPLICATION_JSON_VALUE)
    private List<PolicyViewDTO> getData(@RequestBody List<PolicyViewDTO> paramsMap) {
        return policyViewService.getPolicyViewData(paramsMap);
    }


    @PostMapping(value = "/exportPolicyData/{type}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public byte[] getFilterdPolicies(@RequestBody List<PolicyViewExportDTO> paramsMap, @PathVariable String type) {
        return policyViewService.getExportPolicies(paramsMap, type);
    }

    @PostMapping("/uploadFile")
    public void upload(@RequestParam("uploadfile") MultipartFile file) throws Exception {
        String tempFile;
        FileOutputStream fos;
        String unzipFilePath = null;
        tempFile = System.getProperty("java.io.tmpdir") + File.separator + file.getOriginalFilename();
        File newFile = new File(tempFile);
        fos = new FileOutputStream(newFile);
        IOUtils.write(file.getBytes(), fos);
        if (Objects.requireNonNull(file.getOriginalFilename()).toLowerCase().contains("zip")) {
            unzipFilePath = sharePointUtil.unzipFile(newFile.getAbsolutePath());
            policyViewDao.insertSummaryFilesToDb(unzipFilePath, file.getOriginalFilename());
        } else {
            unzipFilePath = newFile.getAbsolutePath();
            policyViewDao.insertClaimIntroFileToDb(unzipFilePath);
        }
    }
}