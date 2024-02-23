package org.ace.insurance.outbound_insurance.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ace.insurance.outbound_insurance.dto.BeneficiaryDTO;
import org.ace.insurance.outbound_insurance.entity.Beneficiary;
import org.ace.insurance.outbound_insurance.repository.BeneficiaryRepo;
import org.ace.insurance.outbound_insurance.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class BeneficiaryServiceImpl implements BeneficiaryService {
    @Autowired
    private BeneficiaryRepo beneficiaryRepo;


    @Override
    public List<Beneficiary> findAllBeneficiaries() {
        return beneficiaryRepo.findAll();
    }

    @Override
    public Beneficiary create(BeneficiaryDTO beneficiaryDTO) {
        try{
            Beneficiary beneficiary = Beneficiary.builder()
                    .beneficiaryName(beneficiaryDTO.getBeneficiaryName())
                    .beneficiaryDOB(beneficiaryDTO.getBeneficiaryDOB())
                    .beneficiaryRelationship(beneficiaryDTO.getBeneficiaryRelationship())
                    .beneficiaryPhoneNumber(beneficiaryDTO.getBeneficiaryPhoneNumber())
                    .beneficiaryNRC(beneficiaryDTO.getBeneficiaryNRC())
                    .beneficiaryEmail(beneficiaryDTO.getBeneficiaryEmail())
                    .beneficiaryAddress(beneficiaryDTO.getBeneficiaryAddress()).build();
            log.info("beneficiary created: {}",beneficiary);
            return beneficiaryRepo.save(beneficiary);
        }catch (OptimisticLockingFailureException ex){
            throw new RuntimeException("Optimistic locking conflict");
        }

    }



}
