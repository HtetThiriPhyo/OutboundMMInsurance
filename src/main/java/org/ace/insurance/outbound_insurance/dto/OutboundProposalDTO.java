package org.ace.insurance.outbound_insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ace.insurance.outbound_insurance.enumeration.Gender;
import org.ace.insurance.outbound_insurance.enumeration.MaritalStatus;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutboundProposalDTO {
    private UUID id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate estimateDepartureDate;
    private String journeyFrom;
    private String journeyTo;
    private int coveragePlan;
    private String packages;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate policyStartDate;


    //beneficiaryAttribute
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate beneficiaryDOB;
    private String beneficiaryName;
    private String beneficiaryRelationship;
    private String beneficiaryPhoneNumber;
    private String beneficiaryNRC;
    private String beneficiaryEmail;
    private String beneficiaryAddress;

    //InsuredPersonAttribute
    private String insuredName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate insuredDOB;
    private String insuredPhoneNumber;
    private String foreignContactNumber;
    private String fatherName;
    private String race;
    private String occupation;
    private String insuredEmail;
    private String insuredAddress;
    private String insuredAddressAbroad;
    private String passportIssueCountry;
    private String passportNumber;
    private boolean isChild;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate passportIssuedDate;

    @Enumerated(EnumType.STRING)
    private Gender insuredGender;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    //ChildAttribute
    private String childName;
    private String guardianceName;
    private String childRelationship;


    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate childDOB;

    @Enumerated(EnumType.STRING)
    private Gender childGender;

    //AgentAttribute
    private UUID agentID;
}
