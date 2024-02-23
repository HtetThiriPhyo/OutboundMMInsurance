package org.ace.insurance.outbound_insurance.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ace.insurance.outbound_insurance.dto.ChildDTO;
import org.ace.insurance.outbound_insurance.entity.Child;
import org.ace.insurance.outbound_insurance.entity.InsuredPerson;
import org.ace.insurance.outbound_insurance.repository.ChildRepo;
import org.ace.insurance.outbound_insurance.repository.InsuredPersonRepo;
import org.ace.insurance.outbound_insurance.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChildServiceImpl implements ChildService {
    @Autowired
    private ChildRepo childRepo;
    @Autowired
    private InsuredPersonRepo insuredPersonRepo;

    @Override
    public Child create(ChildDTO childDTO) {
        InsuredPerson insuredPerson = insuredPersonRepo.findById(childDTO.getInsuredPersonID())
                .orElseThrow(()->new IllegalArgumentException("Invalid ID."));
        try {
            Child child = Child.builder()
                    .childName(childDTO.getChildName())
                    .childDOB(childDTO.getChildDOB())
                    .childGender(childDTO.getChildGender())
                    .guardianceName(childDTO.getGuardianceName())
                    .insuredPerson(insuredPerson)
                    .childRelationship(childDTO.getChildRelationship()).build();
            log.info("Child created: {} ",child);
            return childRepo.save(child);
        }catch (OptimisticLockingFailureException E){
            throw new RuntimeException("Optimistic locking conflict");
        }

    }
}
