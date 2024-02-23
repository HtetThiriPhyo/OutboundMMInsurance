package org.ace.insurance.outbound_insurance.controller;

import lombok.AllArgsConstructor;
import org.ace.insurance.outbound_insurance.dto.CountryDTO;
import org.ace.insurance.outbound_insurance.entity.Country;
import org.ace.insurance.outbound_insurance.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utility.HttpResponse;

import java.util.List;
import static utility.HttpResponse.createResponse;

@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @PostMapping
    public ResponseEntity<HttpResponse<Country>> createCountries(@RequestBody CountryDTO countryDTO){
        Country country = countryService.create(countryDTO);
        return createResponse(country, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<HttpResponse<List<Country>>> findAllCountry(){
        List<Country> countryList = countryService.findAllCountries();
        return createResponse(countryList,HttpStatus.OK);
    }
}
