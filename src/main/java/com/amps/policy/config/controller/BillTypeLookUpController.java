package com.amps.policy.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.amps.policy.config.model.*;
import com.amps.policy.config.service.PolicyBillTypeService;

@RequestMapping(value = "/BillType")
@RestController
public class BillTypeLookUpController {

    @Autowired
    PolicyBillTypeService policyBillTypeService;

    @GetMapping("/getBillTypeLookUpData")
    private List<BillTypeLookUp> getBillTypeData() {
        return policyBillTypeService.getBillTypeLkpData();
    }

    @GetMapping("/getBillTypeLinkLkp")
    private List<PolicyBillTypeLinkLookUp> getBillTypeLinkLkpKey() {
        return policyBillTypeService.getBillTypeLinkLkpData();
    }

    @GetMapping("/BillTypeData/{policy_id}")
    public List<PolicyBillType> getPolicyBillTypeDataById(@PathVariable int policy_id) {
        return policyBillTypeService.getPolicyBillTypeDataById(policy_id);
    }

    @GetMapping("/getpolicybilltypeactionlkp")
    private List<PolicyActionBillTypeLookUp> getPolicyBillTypeAction() {
        return policyBillTypeService.getPolicyBillTypeActionData();
    }

    @PostMapping(value = "/postBillType", consumes = MediaType.APPLICATION_JSON_VALUE)
    private List<PolicyBillType> postPolicyBillTypeData(@RequestBody List<PolicyBillType> policyBillType) {
        policyBillTypeService.savePolicyBillTypeData(policyBillType);
        return policyBillType;
    }

    @PostMapping(value = "/deleteData/{key}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deletePolicyBillTypeData(@PathVariable int key) {
        policyBillTypeService.deletePolicyBillTypeData(key);
    }

    @GetMapping("/getPolicyBillType")
    public Policy getBillTypeByPolicyId(@PathVariable int id) {
        return policyBillTypeService.getBillTypeByPolicyId(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("uploadfile") MultipartFile file) {
        String deltaPath = policyBillTypeService.readFileAndSaveFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(deltaPath);
    }
}
