package org.ace.insurance.outbound_insurance.repository;

import org.ace.insurance.outbound_insurance.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {

}
