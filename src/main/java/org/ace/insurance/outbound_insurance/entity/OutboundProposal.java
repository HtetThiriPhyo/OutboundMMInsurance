package org.ace.insurance.outbound_insurance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "outbound_proposal")
public class OutboundProposal {
    @Id
    @GeneratedValue(generator = "certificate_id_seq")
    @GenericGenerator(name = "certificate_id_seq", strategy = "org.ace.insurance.outbound_insurance.customIDGenerator.CertificateIDGenerator")
    private String certificateNo;


    @Column(nullable = false, updatable = false) // Ensures that this column cannot be null and cannot be updated
    private UUID id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate submittedDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate policyStartDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate policyEndDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate estimateDepartureDate;
    private String journeyFrom;
    private String journeyTo;
    private int coveragePlan;
    private int packages;
    private double premiumRate;

    //relation
    @ManyToOne
    private InsuredPerson insuredPerson;
    @ManyToOne
    private Agent agent;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate childDOB;
    private String childName;


    //create,update,version
    private UUID createdUserID;
    private LocalDateTime createdDate;
    private UUID updatedUserID;
    private LocalDateTime updatedDate;
    @Version
    private Long version;
    @PrePersist
    protected void onCreate(){
        createdDate = LocalDateTime.now();
        version = 1L;
        if (id==null){
            id = UUID.randomUUID();
        }

    }
    @PreUpdate
    protected void onUpdate(){
        updatedDate = LocalDateTime.now();
        version ++;
    }

    public OutboundProposal() {
        this.id = UUID.randomUUID(); // Generate a UUID for the id field
    }
}
