package org.ace.insurance.outbound_insurance.controller;

import lombok.extern.slf4j.Slf4j;
import org.ace.insurance.outbound_insurance.dto.CurrencyDTO;
import org.ace.insurance.outbound_insurance.entity.Currency;
import org.ace.insurance.outbound_insurance.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utility.HttpResponse;

import java.util.List;

import static utility.HttpResponse.createResponse;

@RestController
@Slf4j
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    @PostMapping
    public ResponseEntity<HttpResponse<Currency>> Create (@RequestBody CurrencyDTO currencyDTO){
        Currency currency = currencyService.Create(currencyDTO);
        return createResponse(currency, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<HttpResponse<List<Currency>>> getAll(){
        List<Currency> currencyList = currencyService.findAll();
        return createResponse(currencyList,HttpStatus.OK);
    }
}
