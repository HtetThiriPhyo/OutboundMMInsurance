package org.ace.insurance.outbound_insurance.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ace.insurance.outbound_insurance.dto.OutboundProposalDTO;
import org.ace.insurance.outbound_insurance.entity.*;
import org.ace.insurance.outbound_insurance.repository.*;
import org.ace.insurance.outbound_insurance.service.OutboundProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@Slf4j
public class OutboundProposalImpl implements OutboundProposalService {
    @Autowired
    private OutboundProposalRepo outboundProposalRepo;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private InsuredPersonRepo insuredPersonRepo;
    @Autowired
    private ChildRepo childRepo;
    @Autowired
    private BeneficiaryRepo beneficiaryRepo;
    @Autowired
    private PremiumRateRepository premiumRateRepository;


    @Override
    public OutboundProposal create(OutboundProposalDTO outboundProposalDTO) {

        try {

            //Save Beneficiary
            Beneficiary beneficiary = Beneficiary.builder()
                    .beneficiaryName(outboundProposalDTO.getBeneficiaryName())
                    .beneficiaryDOB(outboundProposalDTO.getBeneficiaryDOB())
                    .beneficiaryRelationship(outboundProposalDTO.getBeneficiaryRelationship())
                    .beneficiaryPhoneNumber(outboundProposalDTO.getBeneficiaryPhoneNumber())
                    .beneficiaryNRC(outboundProposalDTO.getBeneficiaryNRC())
                    .beneficiaryAddress(outboundProposalDTO.getBeneficiaryAddress())
                    .beneficiaryEmail(outboundProposalDTO.getBeneficiaryEmail()).build();
                    beneficiaryRepo.save(beneficiary);

            InsuredPerson insuredPerson = InsuredPerson.builder()
                    .insuredName(outboundProposalDTO.getInsuredName())
                    .insuredDOB(outboundProposalDTO.getInsuredDOB())
                    .insuredGender(outboundProposalDTO.getInsuredGender())
                    .insuredPhoneNumber(outboundProposalDTO.getInsuredPhoneNumber())
                    .foreignContactNumber(outboundProposalDTO.getForeignContactNumber())
                    .fatherName(outboundProposalDTO.getFatherName())
                    .race(outboundProposalDTO.getRace())
                    .occupation(outboundProposalDTO.getOccupation())
                    .maritalStatus(outboundProposalDTO.getMaritalStatus())
                    .insuredEmail(outboundProposalDTO.getInsuredEmail())
                    .insuredAddress(outboundProposalDTO.getInsuredAddress())
                    .insuredAddressAbroad(outboundProposalDTO.getInsuredAddressAbroad())
                    .passportIssuedDate(outboundProposalDTO.getPassportIssuedDate())
                    .passportNumber(outboundProposalDTO.getPassportNumber())
                    .passportIssueCountry(outboundProposalDTO.getPassportIssueCountry())
                    .beneficiary(beneficiary)
                    .isChild(outboundProposalDTO.isChild()).build();
                    insuredPersonRepo.save(insuredPerson);


            //Save and Condition for Child

            if (outboundProposalDTO.isChild()){
            Child child = Child.builder()
                    .childName(outboundProposalDTO.getChildName())
                    .childDOB(outboundProposalDTO.getChildDOB())
                    .childGender(outboundProposalDTO.getChildGender())
                    .guardianceName(outboundProposalDTO.getGuardianceName())
                    .insuredPerson(insuredPerson)
                    .childRelationship(outboundProposalDTO.getChildRelationship()).build();
                    childRepo.save(child);
            }
            //Save Agent
            if(outboundProposalDTO.getAgentID()!=null){
                Agent agent=agentRepository.findById(outboundProposalDTO.getAgentID())
                        .orElseThrow(()-> new IllegalArgumentException("invalid agent id"));
                OutboundProposal outboundProposal = OutboundProposal.builder()
                        .submittedDate(LocalDate.now())
                        .policyStartDate(outboundProposalDTO.getPolicyStartDate())
                        .policyEndDate(policyEndDate(outboundProposalDTO))
                        .estimateDepartureDate(outboundProposalDTO.getEstimateDepartureDate())
                        .journeyFrom(outboundProposalDTO.getJourneyFrom())
                        .journeyTo(outboundProposalDTO.getJourneyTo())
                        .agent(agent)
                        .coveragePlan(outboundProposalDTO.getCoveragePlan())
                        .packages(Integer.parseInt(outboundProposalDTO.getPackages()))
                        .premiumRate(
                                premiumRateRepository.findPremiumRateByFromAgeAndToAgeAndCoveragePlanAndPackages(calculateAge(outboundProposalDTO),outboundProposalDTO.getCoveragePlan(),Integer.parseInt(outboundProposalDTO.getPackages())))
                        .packages(Integer.parseInt(outboundProposalDTO.getPackages()))
                        .childName(outboundProposalDTO.getChildName())
                        .childDOB(outboundProposalDTO.getChildDOB())
                        .insuredPerson(insuredPerson).build();
                outboundProposalRepo.save(outboundProposal);

                return outboundProposal;
            }


            //Save OutboundProposal
            OutboundProposal outboundProposal = OutboundProposal.builder()
                    .submittedDate(LocalDate.now())
                    .policyStartDate(outboundProposalDTO.getPolicyStartDate())
                    .policyEndDate(policyEndDate(outboundProposalDTO))
                    .estimateDepartureDate(outboundProposalDTO.getEstimateDepartureDate())
                    .journeyFrom(outboundProposalDTO.getJourneyFrom())
                    .journeyTo(outboundProposalDTO.getJourneyTo())
                    .coveragePlan(outboundProposalDTO.getCoveragePlan())
                    .packages(Integer.parseInt(outboundProposalDTO.getPackages()))
                    .premiumRate(
                            premiumRateRepository.findPremiumRateByFromAgeAndToAgeAndCoveragePlanAndPackages(calculateAge(outboundProposalDTO),outboundProposalDTO.getCoveragePlan(),Integer.parseInt(outboundProposalDTO.getPackages())))
                    .packages(Integer.parseInt(outboundProposalDTO.getPackages()))
                    .childDOB(outboundProposalDTO.getChildDOB())
                    .childName(outboundProposalDTO.getChildName())
                    .insuredPerson(insuredPerson).build();
            outboundProposalRepo.save(outboundProposal);

            return outboundProposal;

        }catch (OptimisticLockingFailureException e){
            throw new RuntimeException("Optimistic locking conflict");
        }
    }

    @Override
    public List<OutboundProposal> searchEnquiry(String passportNo, String passportIssueCountry) {
        List<OutboundProposal> outboundProposal = outboundProposalRepo.findByInsuredPersonPassportNumberAndInsuredPersonPassportIssueCountry(passportNo,passportIssueCountry);
        return outboundProposal;
    }

    @Override
    public OutboundProposal findByCertificateNo(String certificateNo) {
        OutboundProposal outboundProposal = outboundProposalRepo.findByCertificateNo(certificateNo);
        return outboundProposal;
    }


    private LocalDate policyEndDate(OutboundProposalDTO outboundProposalDTO){
        LocalDate policyStartDate = outboundProposalDTO.getPolicyStartDate() ;//outboundProposalDTO.getEstimateDepartureDate()
        LocalDate policyEndDate = policyStartDate.plusDays(outboundProposalDTO.getCoveragePlan());
        return policyEndDate;
    }

    private int calculateAge(OutboundProposalDTO outboundProposalDTO){
        if(outboundProposalDTO.isChild()){
            int DOB = outboundProposalDTO.getChildDOB().getYear();
            int date = LocalDate.now().getYear();
            int age = date-DOB;
            return age;
        }
        int DOB =outboundProposalDTO.getInsuredDOB().getYear();
        int date = LocalDate.now().getYear();
        int age = date - DOB;
        return age;
    }


}


