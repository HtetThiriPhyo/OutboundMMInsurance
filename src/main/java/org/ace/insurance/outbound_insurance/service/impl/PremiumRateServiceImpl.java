package org.ace.insurance.outbound_insurance.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ace.insurance.outbound_insurance.dto.PremiumRateDTO;
import org.ace.insurance.outbound_insurance.entity.Currency;
import org.ace.insurance.outbound_insurance.entity.PremiumRate;
import org.ace.insurance.outbound_insurance.repository.CurrencyRepository;
import org.ace.insurance.outbound_insurance.repository.PremiumRateRepository;
import org.ace.insurance.outbound_insurance.service.PremiumRateService;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j

public class PremiumRateServiceImpl implements PremiumRateService {
    private final PremiumRateRepository premiumRateRepository;
    private final CurrencyRepository currencyRepository;

    public PremiumRateServiceImpl(PremiumRateRepository premiumRateRepository, CurrencyRepository currencyRepository) {
        this.premiumRateRepository = premiumRateRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public PremiumRate create(PremiumRateDTO premiumRateDTO) {
        try {
            Currency currency = currencyRepository.findById(premiumRateDTO.getCurrencyID())
                            .orElseThrow(()-> new RuntimeException("NO Currency Found"));
            PremiumRate premiumRate = PremiumRate.of(premiumRateDTO);
            premiumRate.setCurrency(currency);

           log.info("premiumRateCreated :{}",premiumRate);
            return premiumRateRepository.save(premiumRate);
        }catch (OptimisticLockingFailureException ex){
            throw new RuntimeException("Optimistic Locking conflict");
        }

    }

    @Override
    public List<PremiumRate> findAll() {
        return premiumRateRepository.findAll();
    }

    @Override
    public double getPremiumRate(LocalDate age, int days, double packages, boolean isChild) {

        if (isChild == true){
            int childAge = 18;
            return premiumRateRepository.findPremiumRateByFromAgeAndToAgeAndCoveragePlanAndPackages(childAge,days,packages);
        }
            int DOB = age.getYear();
            int date = LocalDate.now().getYear();
            int insurePersonAge = date- DOB;
            return premiumRateRepository.findPremiumRateByFromAgeAndToAgeAndCoveragePlanAndPackages(insurePersonAge, days, packages);

    }




}
