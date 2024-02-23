package org.ace.insurance.outbound_insurance.repository;

import org.ace.insurance.outbound_insurance.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepo extends JpaRepository<Country, UUID>{
}
