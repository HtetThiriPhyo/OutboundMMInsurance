package org.ace.insurance.outbound_insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CurrencyDTO {
    private UUID currencyId;
    private String currencyName;
    private String description;
}
