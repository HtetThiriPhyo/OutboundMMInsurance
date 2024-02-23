package org.ace.insurance.outbound_insurance.dto;

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

public class AgentDTO {

    private UUID id;
    private String agentName;
    private String agentLicense;
    private LocalDate agentDOB;
    private String agentPassword;
    private String agentType;
}
