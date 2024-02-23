package org.ace.insurance.outbound_insurance.repository;

import org.ace.insurance.outbound_insurance.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeneficiaryRepo extends JpaRepository<Beneficiary,UUID> {



}
