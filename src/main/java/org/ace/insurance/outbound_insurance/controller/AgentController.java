package org.ace.insurance.outbound_insurance.controller;

import lombok.extern.slf4j.Slf4j;
import org.ace.insurance.outbound_insurance.dto.AgentDTO;
import org.ace.insurance.outbound_insurance.entity.Agent;
import org.ace.insurance.outbound_insurance.service.AgentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utility.HttpResponse;

import java.time.LocalDate;
import java.util.Optional;

import static utility.HttpResponse.createResponse;


@RestController
@Slf4j
@RequestMapping("/agent")
public class AgentController {
    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }
    @PostMapping
   public ResponseEntity<HttpResponse<Agent>> Create(@RequestBody AgentDTO agentDTO){
       Agent agent= agentService.Create(agentDTO);
        return createResponse(agent,HttpStatus.CREATED);
   }


    @GetMapping("/checkMMKVar")
    public ResponseEntity<AgentDTO> getAgentDetails(@RequestParam String agentLicense, @RequestParam LocalDate agentDOB , @RequestParam String agentType) {
        Optional<AgentDTO> agentDetails = agentService.findAgentByAgentLicenseAndAgentDOB(agentLicense, agentDOB, agentType);

        return agentDetails.map(agentDTO -> new ResponseEntity<>(agentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/checkUSDVar")
    public ResponseEntity<AgentDTO> getAgent(@RequestParam String agentName ,@RequestParam String agentLicense,  @RequestParam String agentType) {
        Optional<AgentDTO> agentDetails = agentService.findAgentByAgentNameAndAgentLicenseAndAgentType(agentName, agentLicense, agentType);

        return agentDetails.map(agentDTO -> new ResponseEntity<>(agentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/checkAsso")
    public ResponseEntity<AgentDTO> getAgentAsso(@RequestParam String agentLicense ,@RequestParam String agentPassword,  @RequestParam String agentType) {
        Optional<AgentDTO> agentDetails = agentService.findAgentByAgentLicenseAndAgentPassword(agentLicense, agentPassword, agentType);

        return agentDetails.map(agentDTO -> new ResponseEntity<>(agentDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//
//    @GetMapping("/checkVerification")
//    public ResponseEntity<HttpResponse<Agent>> checkVerification(@RequestParam("inPutAgentLicense") String agentLicense,
//                                                                 @RequestParam("inPutAgentDOB")  LocalDate agentDOB,
//                                                                 @RequestParam("agentType")String agentType) {
//        Agent agent = agentService.findAgentByAgentLicenseAndAgentDOB(agentLicense, agentDOB,agentType);
//
//        return createResponse(agent,HttpStatus.OK);
//    }
//
//    @GetMapping("/checkAssociation")
//    public ResponseEntity <HttpResponse<Agent>> checkAssociation(@RequestParam("inPutAgentLicense") String agentLicense,
//                                                                 @RequestParam("inPutAgentPassword") String agentPassword,
//                                                                 @RequestParam("agentType")String agentType) {
//        Agent agent = agentService.findAgentByAgentLicenseAndAgentPassword(agentLicense, agentPassword,agentType);
//
//        return createResponse(agent, HttpStatus.OK);
//    }



}
