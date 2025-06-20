package com.amps.policy.config.controller;

import com.amps.policy.config.dao.SearchClaimDao;
import com.amps.policy.config.dto.ClaimSearchDTO;
import com.amps.policy.config.dto.ClaimSearchResultDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/searchClaim")
@RestController
public class SearchClaimController {

    @Autowired
    SearchClaimDao searchDao;

    @PostMapping(value = "/searchClaimData", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ClaimSearchResultDTO> searchClaimData(@RequestBody Map<String, String> paramsMap) {
        ObjectMapper mapper = new ObjectMapper();
        ClaimSearchDTO claimSearchDto = mapper.convertValue(paramsMap, ClaimSearchDTO.class);
        return searchDao.getClaimResults(claimSearchDto);
    }

    @PostMapping(value = "/searchClaimDataSize", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer searchClaimDataSize(@RequestBody Map<String, String> paramsMap) {
        ObjectMapper mapper = new ObjectMapper();
        ClaimSearchDTO claimSearchDto = mapper.convertValue(paramsMap, ClaimSearchDTO.class);
        return searchDao.getClaimResultSize(claimSearchDto);
    }
}