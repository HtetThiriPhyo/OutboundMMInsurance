package org.ace.insurance.outbound_insurance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ace.insurance.outbound_insurance.dto.PremiumRateDTO;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PremiumRates")

public class PremiumRate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int coveragePlan;
    private int fromAge;
    private int toAge;
    private double rate;
    private double packages;
    private UUID createUserId;
    private LocalDateTime createDate;
    private UUID updateUserId;
    private  LocalDateTime updateDate;

    @Version
    private Long version;
    @PrePersist
    protected void onCreate(){
        createDate = LocalDateTime.now();
        version =1L;
    }
    @PreUpdate
    protected void onUpdate(){
        updateDate = LocalDateTime.now();
        version++;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currencyId")
    private Currency currency;

  public static PremiumRate of(PremiumRateDTO premiumRateDTO){
      PremiumRate premiumRate = PremiumRate.builder()
              .coveragePlan(premiumRateDTO.getCoveragePlan())
              .fromAge(premiumRateDTO.getFromAge())
              .toAge(premiumRateDTO.getToAge())
              .rate(premiumRateDTO.getRate())
              .packages(premiumRateDTO.getPackages()).build();
      return premiumRate;
  }
}
