package com.amps.policy.config.controller;

import java.util.List;
import com.amps.policy.config.service.ModifierPayPercentageService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.amps.policy.config.service.LookUpService;
import com.amps.policy.config.service.PolicyUpdateReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.amps.policy.config.dto.statesDTO;
import com.amps.policy.config.model.*;

import static com.amps.policy.config.constants.LookUpConstants.*;

@RequestMapping(value = "/lookUp")
@RestController
public class LookUpController {

    @Autowired
    LookUpService lookUpService;

    @Autowired
    PolicyUpdateReportService policyUpdateReportService;

    @Autowired
    ModifierPayPercentageService modifierPayPercentageService;

    @Value("${pom.version}")
    String version;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("uploadfile") MultipartFile file,
                                         @RequestParam("className") String className) {
        try {
            lookUpService.readFileAndSaveFile(className, file);
            lookUpService.uploadToSharePoint(className, file);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping("/states")
    private List<statesDTO> getStatesData() {
        return lookUpService.getStatesData();
    }

    @GetMapping("/getModifierPayPercent/{Key}")
    private List<ModifierPayPercentage> ModifierPayPercentageData(@PathVariable Integer Key) {
        List<ModifierPayPercentage> modiferPayPercentData = modifierPayPercentageService
                .ModifierPayPercentageData(Key);
        return modiferPayPercentData;
    }

    @GetMapping("/getConfigPOMVersion")
    public String getConfigPOMVersion() {
        return version;
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/getLookUpData/{lkpName}")
    public <T> List<T> getLookup(@PathVariable String lkpName) {
        switch (lkpName) {
            case SPECS_LKP -> {
                return (List<T>) lookUpService.getSpecLkpData();
            }
            case SUB_SPEC_LKP -> {
                return (List<T>) lookUpService.getSubSpecLkpData();
            }
            case MIN_MAX_AGE_LKP -> {
                return (List<T>) lookUpService.getMinMaxAgeData();
            }
            case REVENUE_CODE_LKP -> {
                return (List<T>) lookUpService.getRevCodeData();
            }
            case BILL_TYPE_LKP -> {
                return (List<T>) lookUpService.getBillTypeData();
            }
            case CONDITION_CODE_LKP -> {
                return (List<T>) lookUpService.getCondCodeData();
            }
            case MOD_LKP -> {
                return (List<T>) lookUpService.getModLkpData();
            }
            case POS_LKP_DATA -> {
                return (List<T>) lookUpService.getPosLkpData();
            }
            case POLICY_CATEGORY_LKP -> {
                return (List<T>) lookUpService.getPolicyCategoryData();
            }
            case REASON_CODE_LKP -> {
                return (List<T>) lookUpService.getReasonCodeData();
            }
            case MODIFIER_PRIORITY_LKP -> {
                return (List<T>) lookUpService.getModifierPriorityData();
            }

            case MODIFIER_INTERACTION_LKP -> {
                return (List<T>) lookUpService.getModifierInteractionData();
            }
            case MODIFIER_INTRACTION_TYPE -> {
                return (List<T>) lookUpService.getModifierInteractionTypeData();
            }
            case SAME_OR_SIMILAR_CODE_LKP -> {
                return (List<T>) policyUpdateReportService.getSameOrSimData();
            }
            case CCI_LKP -> {
                return (List<T>) lookUpService.getCciLkpData();
            }
            case CCI_RATIONALE_LKP -> {
                return (List<T>) lookUpService.getCciRationaleLkpData();
            }
            case CLAIM_TYPE_LINK_LKP -> {
                return (List<T>) lookUpService.getClaimTypeLinkLkpData();
            }
            case MUE_RATIONALE_LKP -> {
                return (List<T>) lookUpService.getMueRationaleLkpData();
            }
            case BW_TYPE_LKP -> {
                return (List<T>) lookUpService.getBwTypeLkpData();
            }
            case MAX_UNITS_TYPES -> {
                return (List<T>) lookUpService.getMaxUnitsTypesData();
            }
            case BO_TYPE_LKP -> {
                return (List<T>) lookUpService.getBoLkpData();
            }
            case MAX_UNITS_LKP -> {
                return (List<T>) lookUpService.getMaxUnitsLkpData();
            }
            case MODIFIER_PAY_PERCENTAGE -> {
                return (List<T>) lookUpService.getModifierPayPercentageData();
            }
            case MODIFIER_PAY_PERCENTAGE_LKP_DATA -> {
                return (List<T>) lookUpService.getModifierPayPercentageLkpData();
            }
            case CCI_DEVIATIONS -> {
                return (List<T>) lookUpService.getCciDeviationsData();
            }
            // Add cases for other types as needed
            default -> {
                throw new IllegalArgumentException("Invalid type: " + lkpName);
            }
        }
    }


    @SuppressWarnings("unchecked")
    @PostMapping("/postLookUpData/{lkpName}")
    private <T> List<T> postLookUpData(@RequestBody String jsonPayload, @PathVariable String lkpName)
            throws JsonMappingException, JsonProcessingException {

        switch (lkpName) {
            case SPECS_LKP -> {
                SpecLookUp specLookUp = objectMapper.readValue(jsonPayload, SpecLookUp.class);
                lookUpService.saveSpecLkpData(specLookUp);
                return (List<T>) lookUpService.getSpecLkpData(); // <--- explicitly call get method after save
            }
            case SUB_SPEC_LKP -> {
                SubSpecMap subSpecMap = objectMapper.readValue(jsonPayload, SubSpecMap.class);
                lookUpService.savesubSpecLkpData(subSpecMap);
                return (List<T>) lookUpService.getSubSpecLkpData();
            }
            case MIN_MAX_AGE_LKP -> {
                MinMaxAgeLookUp minMaxAgeLookUp = objectMapper.readValue(jsonPayload, MinMaxAgeLookUp.class);
                lookUpService.saveMinMaxAgeLkpData(minMaxAgeLookUp);
                return (List<T>) lookUpService.getMinMaxAgeData();
            }
            case REVENUE_CODE_LKP -> {
                RevenueCodeLookUp revenueCodeLookUp = objectMapper.readValue(jsonPayload, RevenueCodeLookUp.class);
                lookUpService.saveRevCodeLkpData(revenueCodeLookUp);
                return (List<T>) lookUpService.getRevCodeData();

            }
            case BILL_TYPE_LKP -> {
                BillTypeLookUp billTypeLookUp = objectMapper.readValue(jsonPayload, BillTypeLookUp.class);
                lookUpService.saveBillTypeLkpData(billTypeLookUp);
                return (List<T>) lookUpService.getBillTypeData();
            }
            case CONDITION_CODE_LKP -> {
                ConditionCodeLookUp conditionCodeLookUp = objectMapper.readValue(jsonPayload, ConditionCodeLookUp.class);
                lookUpService.saveCondCodeLkpData(conditionCodeLookUp);
                return (List<T>) lookUpService.getCondCodeData();

            }
            case EDIT_MOD_LKP_DATA -> {
                ModLookUp modLookUp = objectMapper.readValue(jsonPayload, ModLookUp.class);
                lookUpService.EditModLkpData(modLookUp);
                return (List<T>) lookUpService.getModLkpData();

            }
            case POST_MOD_LKP_DATA -> {
                ModLookUp modLookUp = objectMapper.readValue(jsonPayload, ModLookUp.class);
                lookUpService.saveModLkpData(modLookUp);
                return (List<T>) lookUpService.getModLkpData();
            }
            case POS_LKP_DATA -> {
                PosLookUp posLookUp = objectMapper.readValue(jsonPayload, PosLookUp.class);
                lookUpService.savePosLkpData(posLookUp);
                return (List<T>) lookUpService.getPosLkpData();
            }
            case POLICY_CATEGORY_LKP -> {
                PolicyCategoryLookUp policyCategoryLookUp = objectMapper.readValue(jsonPayload, PolicyCategoryLookUp.class);
                lookUpService.savePolicyCategoryLkpData(policyCategoryLookUp);
                return (List<T>) lookUpService.getPolicyCategoryData();
            }
            case REASON_CODE_LKP -> {
                ReasonCodeLookUp reasonCodeLookUp = objectMapper.readValue(jsonPayload, ReasonCodeLookUp.class);
                lookUpService.saveReasonCodeLkp(reasonCodeLookUp);
                return (List<T>) lookUpService.getReasonCodeData();
            }
            case CCI_RATIONALE_LKP -> {
                CciRationaleLookup cciRationaleLookUp = objectMapper.readValue(jsonPayload, CciRationaleLookup.class);
                lookUpService.saveCciRationaleLkp(cciRationaleLookUp);
                return (List<T>) lookUpService.getCciRationaleLkpData();
            }
            case CCI_LKP -> {
                CCI cci = objectMapper.readValue(jsonPayload, CCI.class);
                lookUpService.saveCciLkp(cci);
                return (List<T>) lookUpService.getCciLkpData();
            }
            case MUE_RATIONALE_LKP -> {
                MueRationalLookUp mueRationalLookUp = objectMapper.readValue(jsonPayload, MueRationalLookUp.class);
                lookUpService.saveMueRationaleLkp(mueRationalLookUp);
                return (List<T>) lookUpService.getMueRationaleLkpData();
            }
            case BW_TYPE_LKP -> {
                BwTypeLookUp bwTypeLookUp = objectMapper.readValue(jsonPayload, BwTypeLookUp.class);
                lookUpService.saveBwtypeLkp(bwTypeLookUp);
                return (List<T>) lookUpService.getBwTypeLkpData();
            }
            case MAX_UNITS_TYPES -> {
                MaxUnitsTypes maxUnitsTypes = objectMapper.readValue(jsonPayload, MaxUnitsTypes.class);
                lookUpService.saveMaxUnitsTypes(maxUnitsTypes);
                return (List<T>) lookUpService.getMaxUnitsTypesData();

            }
            case BO_TYPE_LKP -> {
                BoTypeLookUp boTypeLookUp = objectMapper.readValue(jsonPayload, BoTypeLookUp.class);
                lookUpService.saveBoLkp(boTypeLookUp);
                return (List<T>) lookUpService.getBoLkpData();
            }
            case MAX_UNITS_LKP -> {
                MaxUnitsLookUp maxUnitsLookUp = objectMapper.readValue(jsonPayload, MaxUnitsLookUp.class);
                lookUpService.saveMaxUnitsLkp(maxUnitsLookUp);
                return (List<T>) lookUpService.getMaxUnitsLkpData();
            }
            case CCI_DEVIATIONS -> {
                List<CCIDeviationsDTO> cciDeviationsDTO = objectMapper.readValue(jsonPayload, new TypeReference<>() {
                });
                cciDeviationsDTO.forEach(cciDeviationsDTO1 -> lookUpService.saveCciDeviationsData(cciDeviationsDTO1));
                return (List<T>) lookUpService.getCciDeviationsData();
            }
            case CCI_DEVIATIONS_EDIT -> {
                List<CCIDeviationsDTO> cciDeviationsDTO = objectMapper.readValue(jsonPayload, new TypeReference<>() {
                });
                cciDeviationsDTO.forEach(cciDeviationsDTO1 -> lookUpService.editCciDeviationsData(cciDeviationsDTO1));
                return (List<T>) lookUpService.getCciDeviationsData();
            }
            default -> throw new IllegalArgumentException("Invalid type: " + lkpName);
        }
    }
}
