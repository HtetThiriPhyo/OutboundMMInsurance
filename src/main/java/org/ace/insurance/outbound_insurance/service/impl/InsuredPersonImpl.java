package org.ace.insurance.outbound_insurance.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ace.insurance.outbound_insurance.dto.InsuredPersonDTO;
import org.ace.insurance.outbound_insurance.entity.Beneficiary;
import org.ace.insurance.outbound_insurance.entity.InsuredPerson;
import org.ace.insurance.outbound_insurance.repository.BeneficiaryRepo;
import org.ace.insurance.outbound_insurance.repository.InsuredPersonRepo;
import org.ace.insurance.outbound_insurance.service.InsuredPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InsuredPersonImpl implements InsuredPersonService {
    @Autowired
    private InsuredPersonRepo insuredPersonRepo;
    @Autowired
    private BeneficiaryRepo beneficiaryRepo;

    @Override
    public InsuredPerson create(InsuredPersonDTO insuredPersonDTO) {
        Beneficiary beneficiary = beneficiaryRepo.findById(insuredPersonDTO.getBeneficiaryID())
                .orElseThrow(()-> new IllegalArgumentException("Invalid InsuredPerson ID."));
        try {
            InsuredPerson insuredPerson = InsuredPerson.builder()
                    .insuredName(insuredPersonDTO.getInsuredName())
                    .insuredDOB(insuredPersonDTO.getInsuredDOB())
                    .insuredGender(insuredPersonDTO.getInsuredGender())
                    .insuredPhoneNumber(insuredPersonDTO.getInsuredPhoneNumber())
                    .foreignContactNumber(insuredPersonDTO.getForeignContactNumber())
                    .fatherName(insuredPersonDTO.getFatherName())
                    .race(insuredPersonDTO.getRace())
                    .occupation(insuredPersonDTO.getOccupation())
                    .maritalStatus(insuredPersonDTO.getMaritalStatus())
                    .insuredEmail(insuredPersonDTO.getInsuredEmail())
                    .insuredAddress(insuredPersonDTO.getInsuredAddress())
                    .insuredAddressAbroad(insuredPersonDTO.getInsuredAddressAbroad())
                    .passportIssuedDate(insuredPersonDTO.getPassportIssuedDate())
                    .passportNumber(insuredPersonDTO.getPassportNumber())
                    .beneficiary(beneficiary)
                    .isChild(insuredPersonDTO.isChild()).build();
            log.info("insured person created: {}",insuredPerson);
                    return insuredPersonRepo.save(insuredPerson);
        }catch (OptimisticLockingFailureException e){
            throw new RuntimeException("Optimistic locking conflict");
        }

    }
}
