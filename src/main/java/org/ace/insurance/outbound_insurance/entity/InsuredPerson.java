package org.ace.insurance.outbound_insurance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ace.insurance.outbound_insurance.enumeration.Gender;
import org.ace.insurance.outbound_insurance.enumeration.MaritalStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "insured_person")
public class InsuredPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String insuredName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate insuredDOB;
    private String insuredPhoneNumber;
    private String foreignContactNumber;
    private String fatherName;
    private String race;
    private String occupation;
    private String insuredEmail;
    private String insuredAddress;
    private String insuredAddressAbroad;
    private String passportNumber;
    private String passportIssueCountry;
    private boolean isChild;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate passportIssuedDate;

    @Enumerated(EnumType.STRING)
    private Gender insuredGender;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;


    //Relation
    @OneToOne
    private Beneficiary beneficiary;

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
