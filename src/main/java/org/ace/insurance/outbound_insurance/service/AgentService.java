package org.ace.insurance.outbound_insurance.service;

import org.ace.insurance.outbound_insurance.dto.AgentDTO;
import org.ace.insurance.outbound_insurance.entity.Agent;

import java.time.LocalDate;
import java.util.Optional;

public interface AgentService {
    Agent Create(AgentDTO agentDTO);

//    Agent findAgentByAgentLicenseAndAgentDOB(String agentLicense, LocalDate agentDOB,String agentType);
//    Agent findAgentByAgentLicenseAndAgentPassword(String agentLicense, String agentPassword,String agentType);
//
//    Agent findAgentByAgentNameAndAgentLicenseAndAgentType(String agentName, String agentLicenseNo, String agentType);

    Optional<AgentDTO> findAgentByAgentLicenseAndAgentDOB(String agentLicense, LocalDate agentDOB,String agentType);
    Optional<AgentDTO> findAgentByAgentLicenseAndAgentPassword(String agentLicense, String agentPassword,String agentType);

    Optional<AgentDTO> findAgentByAgentNameAndAgentLicenseAndAgentType(String agentName, String agentLicenseNo, String agentType);

}
