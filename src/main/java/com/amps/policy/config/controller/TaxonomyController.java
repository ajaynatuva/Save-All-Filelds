package com.amps.policy.config.controller;

import com.amps.policy.config.model.Taxonomy;
import com.amps.policy.config.service.impl.TaxonomyServiceImpl;
import com.amps.policy.config.service.impl.TaxonomyStageServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RequestMapping("/taxonomy")
@RestController
public class TaxonomyController {

    @Autowired
    TaxonomyServiceImpl taxonomyService;
    @Autowired
    TaxonomyStageServiceImpl taxonomyStageServiceImpl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/taxonomyData/{policyId}")
    public List<Taxonomy> getAllTaxonomy(@PathVariable Integer policyId) {
        return taxonomyService.getTaxonomyOfPolicy(policyId);
    }

    @PostMapping("/save")
    public List<Taxonomy> saveTaxonomy(@RequestBody String jsonPayload) throws JsonProcessingException {
        List<Taxonomy> taxonomyList = objectMapper.readValue(jsonPayload, new TypeReference<>() {
        });
        taxonomyList.forEach(taxonomy1 -> taxonomyService.saveTaxonomy(taxonomy1));
        Integer policyId = taxonomyList.getFirst().getPolicyId();
        return taxonomyService.getTaxonomyOfPolicy(policyId);
    }

    @PostMapping("/delete/{id}/{policyId}")
    public List<Taxonomy> deleteTaxonomyByIdAndPolicyId(@PathVariable Integer id, @PathVariable Integer policyId, @RequestBody String taxonomyCode) {
        if (StringUtils.hasLength(taxonomyCode) && !taxonomyCode.equals("\"undefined\"")) {
            taxonomyService.deleteByTaxonomy(taxonomyCode.replaceAll("\"", ""), policyId);
        } else {
            taxonomyService.deleteTaxonomyById(id);
        }
        return taxonomyService.getTaxonomyOfPolicy(policyId);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTaxonomy(@RequestParam("uploadfile") MultipartFile file,
                                                 @RequestParam("email") String emailId) {
        String deltaPath = taxonomyStageServiceImpl.readFileAndSaveFile(file, emailId);
        if (deltaPath != null)
            return ResponseEntity.status(HttpStatus.OK).body(deltaPath);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
    }

    @PostMapping("/stagetotarget/{policyId}")
    public ResponseEntity<List<Taxonomy>> loadDataToTaxonomyTarget(@PathVariable Integer policyId)
            throws ClassNotFoundException, IOException, SQLException {
        List<Taxonomy> taxonomyData = taxonomyStageServiceImpl.loadDataToTarget(policyId);
        if (!taxonomyData.isEmpty()) {
            return ResponseEntity.ok(taxonomyData);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @PostMapping("/disable/{taxonomyKey}/{policyId}")
    public List<Taxonomy> disableTaxonomy(@PathVariable Integer taxonomyKey, @PathVariable Integer policyId) {
        taxonomyService.disableTaxonomyByKey(taxonomyKey);
        return taxonomyService.getTaxonomyOfPolicy(policyId);
    }
}
