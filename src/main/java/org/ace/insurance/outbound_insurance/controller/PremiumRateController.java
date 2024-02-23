package org.ace.insurance.outbound_insurance.controller;

import lombok.extern.slf4j.Slf4j;
import org.ace.insurance.outbound_insurance.dto.PremiumRateDTO;
import org.ace.insurance.outbound_insurance.entity.PremiumRate;
import org.ace.insurance.outbound_insurance.service.PremiumRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utility.HttpResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static utility.HttpResponse.createResponse;

@RestController
@Slf4j
@RequestMapping("/premiumRate")

public class PremiumRateController {

    private final PremiumRateService premiumRateService;

    public PremiumRateController(PremiumRateService premiumRateService) {
        this.premiumRateService = premiumRateService;
    }

    @PostMapping
    public ResponseEntity<HttpResponse<PremiumRate>> Create(@RequestBody PremiumRateDTO premiumRateDTO){
        PremiumRate premiumRate =premiumRateService.create(premiumRateDTO);
        return createResponse(premiumRate, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<HttpResponse<Double>> getPremiumRate(@RequestParam String age , @RequestParam int days, @RequestParam double packages, @RequestParam boolean isChild){
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
       LocalDate parsedDate = LocalDate.parse(age,formatter);
        double rate = premiumRateService.getPremiumRate(parsedDate,days,packages,isChild);
        return createResponse(rate,HttpStatus.OK);
    }
}
