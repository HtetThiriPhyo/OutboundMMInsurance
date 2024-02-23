package org.ace.insurance.outbound_insurance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ace.insurance.outbound_insurance.enumeration.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "child")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String childName;
    private String guardianceName;
    private String childRelationship;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate childDOB;

    @Enumerated(EnumType.STRING)
    private Gender childGender;


    //Relation
    @ManyToOne
    private InsuredPerson insuredPerson;

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
