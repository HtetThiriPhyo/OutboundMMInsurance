package org.ace.insurance.outbound_insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiaryDTO {
    private UUID id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate beneficiaryDOB;
    private String beneficiaryName;
    private String beneficiaryRelationship;
    private String beneficiaryPhoneNumber;
    private String beneficiaryNRC;
    private String beneficiaryEmail;
    private String beneficiaryAddress;
}
