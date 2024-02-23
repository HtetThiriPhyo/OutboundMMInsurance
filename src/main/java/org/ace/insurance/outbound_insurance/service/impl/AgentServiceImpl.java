package org.ace.insurance.outbound_insurance.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ace.insurance.outbound_insurance.dto.AgentDTO;
import org.ace.insurance.outbound_insurance.entity.Agent;
import org.ace.insurance.outbound_insurance.repository.AgentRepository;
import org.ace.insurance.outbound_insurance.service.AgentService;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;

    public AgentServiceImpl(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public Agent Create(AgentDTO agentDTO) {
        try{
            Agent agent = Agent.builder()
                    .agentName(agentDTO.getAgentName())
                    .agentPassword(agentDTO.getAgentPassword())
                    .agentLicense(agentDTO.getAgentLicense())
                    .agentDOB(agentDTO.getAgentDOB())
                    .agentType(agentDTO.getAgentType()).build();
            log.info("agent created : {}",agent);
            return agentRepository.save(agent);
        }catch (OptimisticLockingFailureException ex){
            throw new RuntimeException("Optimistic Locking conflict");
        }

    }

    @Override
    public Optional<AgentDTO> findAgentByAgentLicenseAndAgentDOB( String agentLicense,  LocalDate agentDOB,  String agentType) {
        Agent agent = agentRepository.findAgentByAgentLicenseAndAgentDOBAndAgentType(agentLicense,agentDOB,agentType);
        if (agent != null) {
            AgentDTO response = new AgentDTO();
            response.setId(agent.getId());
            response.setAgentLicense(agent.getAgentLicense());
            response.setAgentPassword(agent.getAgentPassword());
            response.setAgentName(agent.getAgentName());
            response.setAgentType(agent.getAgentType());
            response.setAgentDOB(agent.getAgentDOB());
            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional <AgentDTO> findAgentByAgentLicenseAndAgentPassword( String agentLicense,  String agentPassword, String agentType) {
        Agent agent =  agentRepository.findAgentByAgentLicenseAndAgentPasswordAndAgentType(agentLicense,agentPassword,agentType);
        if (agent != null) {
            AgentDTO response = new AgentDTO();
            response.setId(agent.getId());
            response.setAgentLicense(agent.getAgentLicense());
            response.setAgentPassword(agent.getAgentPassword());
            response.setAgentName(agent.getAgentName());
            response.setAgentType(agent.getAgentType());
            response.setAgentDOB(agent.getAgentDOB());
            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AgentDTO> findAgentByAgentNameAndAgentLicenseAndAgentType( String agentName,  String agentLicense, String agentType) {
        Agent agent = agentRepository.findAgentByAgentNameAndAgentLicenseAndAgentType(agentName,agentLicense,agentType);
        if (agent != null) {
            AgentDTO response = new AgentDTO();
            response.setId(agent.getId());
            response.setAgentLicense(agent.getAgentLicense());
            response.setAgentPassword(agent.getAgentPassword());
            response.setAgentName(agent.getAgentName());
            response.setAgentType(agent.getAgentType());
            response.setAgentDOB(agent.getAgentDOB());
            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }

//    @Override
//    public Optional<AgentDTO> checkAgentDetails(@RequestParam String agentLicenseNo, @RequestParam String agentPassword) {
//        Agent agent = agentRepository.findAgentByAgentLicenseNoAndAgentPassword(agentLicenseNo, agentPassword);
//
//        if (agent != null) {
//            AgentDTO response = new AgentDTO();
//            response.setId(agent.getId());
//            response.setAgentLicenseNo(agent.getAgentLicenseNo());
//            response.setAgentPassword(agent.getAgentPassword());
//            response.setAgentName(agent.getAgentName());
//            return Optional.of(response);
//        } else {
//            return Optional.empty();
//        }
//    }


}
