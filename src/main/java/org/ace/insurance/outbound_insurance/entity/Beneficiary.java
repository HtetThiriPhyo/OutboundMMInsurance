package org.ace.insurance.outbound_insurance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "beneficiary")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate beneficiaryDOB;

    private String beneficiaryName;
    private String beneficiaryRelationship;
    private String beneficiaryPhoneNumber;
    private String beneficiaryNRC;
    private String beneficiaryEmail;
    private String beneficiaryAddress;

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
    }
    @PreUpdate
    protected void onUpdate(){
        updatedDate = LocalDateTime.now();
        version ++;
    }

}
