package org.ace.insurance.outbound_insurance.repository;

import org.ace.insurance.outbound_insurance.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface AgentRepository extends JpaRepository<Agent, UUID> {
    Agent findAgentByAgentLicenseAndAgentDOBAndAgentType( String agentLicense,LocalDate agentDOB,String agentType);

    Agent findAgentByAgentLicenseAndAgentPasswordAndAgentType( String agentLicense, String agentPassword,String agentType);

    Agent findAgentByAgentNameAndAgentLicenseAndAgentType(String agentName, String agentLicense, String agentType);


}
