package org.ace.insurance.outbound_insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ace.insurance.outbound_insurance.enumeration.Gender;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChildDTO {
    private UUID id;
    private String childName;
    private String guardianceName;
    private String childRelationship;


    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate childDOB;

    @Enumerated(EnumType.STRING)
    private Gender childGender;

    private UUID insuredPersonID;


}
