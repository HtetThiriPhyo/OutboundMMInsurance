package org.ace.insurance.outbound_insurance.controller;

import lombok.AllArgsConstructor;
import org.ace.insurance.outbound_insurance.dto.BeneficiaryDTO;
import org.ace.insurance.outbound_insurance.entity.Beneficiary;
import org.ace.insurance.outbound_insurance.service.BeneficiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utility.HttpResponse;

import java.util.List;

import static utility.HttpResponse.createResponse;

@RestController
@RequestMapping("/beneficiaries")
@AllArgsConstructor
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @PostMapping
    public ResponseEntity<HttpResponse<Beneficiary>>createBeneficiaries(@RequestBody BeneficiaryDTO beneficiaryDTO){
        Beneficiary beneficiary = beneficiaryService.create(beneficiaryDTO);
        return createResponse(beneficiary, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<HttpResponse<List<Beneficiary>>>findAllBeneficiary(){
        List<Beneficiary> beneficiaryList = beneficiaryService.findAllBeneficiaries();
        return createResponse(beneficiaryList,HttpStatus.OK);
    }


}
