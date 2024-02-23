package org.ace.insurance.outbound_insurance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agents")

public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String agentName;
    private String agentLicense;
    private LocalDate agentDOB;
    private String agentPassword;
    private String agentType;

    private  UUID createdUserId;
    private LocalDateTime createdDate;
    private UUID updatedUserId;
    private LocalDateTime updatedDate;

    @Version
    private Long version;

    @PrePersist
    public void onCreate(){
        createdDate = LocalDateTime.now();
        version = 1l;
    }

    @PreUpdate
    public void onUpdate(){
        updatedDate = LocalDateTime.now();
        version ++;
    }



}
