package org.ace.insurance.outbound_insurance.controller;


import lombok.AllArgsConstructor;
import org.ace.insurance.outbound_insurance.dto.InsuredPersonDTO;
import org.ace.insurance.outbound_insurance.entity.InsuredPerson;
import org.ace.insurance.outbound_insurance.service.InsuredPersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utility.HttpResponse;

import static utility.HttpResponse.createResponse;

@RestController
@RequestMapping("/insuredPerson")
@AllArgsConstructor
public class InsuredPersonController {

    private final InsuredPersonService insuredPersonService;

    @PostMapping
    public ResponseEntity<HttpResponse<InsuredPerson>>createInsuredPerson(@RequestBody InsuredPersonDTO insuredPersonDTO){
        InsuredPerson insuredPerson = insuredPersonService.create(insuredPersonDTO);
        return createResponse(insuredPerson, HttpStatus.CREATED);
    }


}
