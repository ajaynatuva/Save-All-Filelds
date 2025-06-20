package com.amps.policy.config.controller;

import com.amps.policy.config.service.ClientDataMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/clientAssignment")
@RestController
public class ClientAssignmentDataMigrationController {

    @Autowired
    ClientDataMigrationService clientDataMigrationService;

    @RequestMapping("/migrate")
    public void MigratePolicyClientDataToALlPolicies(){
        clientDataMigrationService.clientMigration();
    }
}
