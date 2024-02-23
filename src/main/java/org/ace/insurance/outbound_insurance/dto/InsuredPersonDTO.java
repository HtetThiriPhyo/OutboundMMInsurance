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
public class InsuredPersonDTO {
    private UUID id;
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
    private String passportNumber;
    private String passportIssueCountry;
    private boolean isChild;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate passportIssuedDate;

    @Enumerated(EnumType.STRING)
    private Gender insuredGender;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    private UUID beneficiaryID;


}
