package com.amps.policy.config.controller;

import com.amps.policy.config.dto.ChangesDTO;
import com.amps.policy.config.model.TaxId;
import com.amps.policy.config.service.TaxIdService;
import com.amps.policy.config.service.TaxIdStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/taxId")
public class TaxIdController {

    @Autowired
    TaxIdService taxIdService;

    @Autowired
    TaxIdStageService taxIdStageService;

    @GetMapping(value = "/getTaxIdByPolicyId/{policyId}")
    public List<TaxId> getTaxIdListData(@PathVariable Integer policyId) {
        return taxIdService.getTaxIdGroupedDetails(policyId);
    }

    @PostMapping(value = "/deleteTaxIdById")
    public List<TaxId> deleteByTaxId(@RequestBody List<TaxId> taxIdList) {
        taxIdService.deleteByTaxId(taxIdList);
        return taxIdService.getTaxIdGroupedDetails(taxIdList.getFirst().getPolicyIdFk());
    }

    @PostMapping(value = "/saveTaxIdDetails")
    public List<TaxId> saveTaxIdDetails(@RequestBody List<TaxId> taxIdList) {
        taxIdService.saveTaxIdData(taxIdList);
        return taxIdService.getTaxIdGroupedDetails(taxIdList.getFirst().getPolicyIdFk());
    }

    @PostMapping("/uploadTaxIdDataToStage")
    public ResponseEntity<String> uploadTaxIdData(@RequestParam("uploadfile") MultipartFile file,
                                                  @RequestParam("email") String emailId) {

        String deltaPath = taxIdStageService.readFileAndSaveFile(file, emailId);
        if (deltaPath != null)
            return ResponseEntity.status(HttpStatus.OK).body(deltaPath);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
    }

    @PostMapping("/saveTaxIdDataToTarget/{policyId}")
    public ResponseEntity <List<TaxId>> loadTaxIdDataToTarget(@PathVariable Integer policyId, @RequestBody ChangesDTO changesTaxIdData)  throws ClassNotFoundException, IOException, SQLException{

        taxIdService.saveNewTaxIDChanges(changesTaxIdData);

        List<TaxId> data =  taxIdService.loadDataToTarget(policyId);

        if (!data.isEmpty()) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }
}
