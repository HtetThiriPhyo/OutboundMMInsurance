package org.ace.insurance.outbound_insurance.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ace.insurance.outbound_insurance.dto.CountryDTO;
import org.ace.insurance.outbound_insurance.entity.Country;
import org.ace.insurance.outbound_insurance.repository.CountryRepo;
import org.ace.insurance.outbound_insurance.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepo countryRepo;

    @Override
    public List<Country> findAllCountries() {

        return countryRepo.findAll();
    }

    @Override
    public Country create(CountryDTO countryDTO) {
       try {
           Country country = Country.builder()
                   .countryName(countryDTO.getCountryName())
                   .countryCode(countryDTO.getCountryCode())
                   .shortCountryName(countryDTO.getShortCountryName()).build();
           log.info("country created: {}",country);
           return countryRepo.save(country);
        }catch (OptimisticLockingFailureException ex){
           throw new RuntimeException("Optimistic locking conflict");
       }
    }
}
