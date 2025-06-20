package com.amps.policy.config.controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import com.amps.policy.config.model.*;
import com.amps.policy.config.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.amps.policy.config.dto.PolicyConfigDTO;

@RequestMapping("/newPolicy")
@RestController
public class NewPolicyController {

    @Autowired
    PolicyService policyService;

    @GetMapping("/gender")
    private List<GenderLookUp> getGenderDetails() {
        return policyService.getGenderDetails();
    }

    @GetMapping("/lob")
    private List<LobLookUp> getLobDetails() {
        return policyService.getLobDetails();
    }

    @GetMapping("/medicalpolicy")
    private List<MedicalPolicyLookup> getMedicalPolicyDetails() {
        return policyService.getMedicalPolicyDetails();
    }

    @GetMapping("/npilink")
    private List<NpiLinkLookUp> getNpiLinkDetails() {
        return policyService.getNpiLinkDetails();
    }

    @GetMapping("/posLink")
    private List<PosLinkLookUp> getPosLinkDetails() {
        return policyService.getPosLinkDetails();
    }

    @GetMapping("/policyValid")
    private List<Policy> getPolicyDetails() {
        return policyService.getPolicyDetails();
    }

    @GetMapping("/producttype")
    private List<ProductTypeLookUp> productTypeDetails() {
        return policyService.getProductTypeDetails();
    }

    @GetMapping("/subpolicies")
    private List<SubPolicies> subPoliciesDetails() {
        return policyService.getSubPolicyDetails();
    }

    @GetMapping("/taxlink")
    private List<TaxLinkLookUp> taxLinkDetails() {
        return policyService.getTaxLinkDetails();
    }

    @GetMapping("/taxonomy")
    private List<TaxonomyLinkLookUp> getTaxonomyDetails() {
        return policyService.getTaxonomyDetails();
    }

    @GetMapping("/policy/{id}")
    private Policy getPolicyById(@PathVariable int id) {
        var policy = policyService.getPolicyById(id);
        if (policy != null) {
            policy.setClonedPolVer(policyService.findByPolicyVersion(id));
        }
        return policy;
    }

    @PostMapping("/policies")
    public int createPolicy(@RequestBody Policy policy) {
        policyService.updatePolicy(policy);
        return policy.getPolicyId();
    }

    @GetMapping("/policyNumber/{policynumber}/{version}")
    public List<Policy> findPolicyByNumberAndVersion(@PathVariable int policynumber, @PathVariable String version) {
        return policyService.getPolicyNumber(policynumber, version);
    }

    @PostMapping(value = "/updateNewPolicies", consumes = MediaType.APPLICATION_JSON_VALUE)
    public int saveNewPolicy(@RequestBody PolicyConfigDTO policyConfigDTO) {
        return policyService.savePolicy(policyConfigDTO);
    }

    @GetMapping("/policyValidation/{policynumber}")
    public boolean validatePolicyNumber(@PathVariable int policynumber) {
        return policyService.isPolicyNumberValid(policynumber);
    }

    @GetMapping("/addOnCodes")
    public List<AddOnCodes> getAllAddOnCodes() {
        return policyService.getAddOnCodes();
    }

    @GetMapping("/claimType")
    public List<ClaimTypeLookUp> getAllClaimTypes() {
        return policyService.getClaimTypes();
    }

    @GetMapping("/revenueCodeClaimLink")
    public List<RevenueCodeClaimLink> getAllRevenueCodeClaimLinks() {
        return policyService.getRevenueCodeClaimLinks();
    }

    @GetMapping("/cptLink")
    public List<CptLinkLookUp> getAllCptLinks() {
        return policyService.getCptLinkDetails();
    }

    @GetMapping("/changeModifier")
    public List<ChangeModifierLookUp> getAllChangeModifiers() {
        return policyService.getChangeModifiers();
    }

    @PostMapping(value = "/saveCustomPolicyTabsData/{createdDate}/{cloned}/{clonedTaxonomy}/{newPolicyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    private Integer saveAllTabsData(@RequestBody PolicyConfigDTO policyConfigDTO, @PathVariable Date createdDate,
                                    @PathVariable String cloned, @PathVariable String clonedTaxonomy, @PathVariable Integer newPolicyId) throws ParseException {
        return policyService.saveTabsData(policyConfigDTO, createdDate, cloned, clonedTaxonomy, newPolicyId);
    }
}