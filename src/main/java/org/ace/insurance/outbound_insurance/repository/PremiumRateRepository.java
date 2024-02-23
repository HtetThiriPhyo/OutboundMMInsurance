package org.ace.insurance.outbound_insurance.repository;

import org.ace.insurance.outbound_insurance.entity.PremiumRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PremiumRateRepository extends JpaRepository<PremiumRate, UUID> {
    @Query("SELECT pr.rate  FROM PremiumRate pr WHERE pr.fromAge <= :age AND pr.toAge >= :age AND pr.coveragePlan = :days AND pr.packages= :packages")
    double findPremiumRateByFromAgeAndToAgeAndCoveragePlanAndPackages(@Param("age") int age, @Param("days") int days, @Param("packages") double packages);
}
