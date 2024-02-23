package org.ace.insurance.outbound_insurance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "currencies")

public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String currencyName;
    private String description;

    private  UUID createdUserId;
    private LocalDateTime createdDate;

    private UUID updatedUserId;
    private LocalDateTime updatedDate;

    @Version
    private Long version;
    @PrePersist
    protected  void onCreate(){
        createdDate = LocalDateTime.now();
        version =1l;

    }
    @PreUpdate
    protected void onUpdate(){
        updatedDate = LocalDateTime.now();
        version++;
    }
    @OneToMany(mappedBy = "currency")
    @JsonIgnore
     private List<PremiumRate> premiumRateList;

}
