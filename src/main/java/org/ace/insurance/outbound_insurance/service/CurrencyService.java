package org.ace.insurance.outbound_insurance.service;

import org.ace.insurance.outbound_insurance.dto.CurrencyDTO;
import org.ace.insurance.outbound_insurance.entity.Currency;

import java.util.List;

public interface CurrencyService {
    Currency Create(CurrencyDTO currencyDto);

    List<Currency> findAll();
}
