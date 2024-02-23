package org.ace.insurance.outbound_insurance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String countryName;
    private String countryCode;
    private String shortCountryName;

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
