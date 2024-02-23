package org.ace.insurance.outbound_insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    private UUID id;
    private String countryName;
    private String countryCode;
    private String shortCountryName;

    private UUID createdUserID;
    private LocalDateTime createdDate;

    private UUID updatedUserID;
    private LocalDateTime updatedDate;

    private Long version;

}
