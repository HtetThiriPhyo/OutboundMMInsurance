package org.ace.insurance.outbound_insurance.service;
import org.ace.insurance.outbound_insurance.dto.CountryDTO;
import org.ace.insurance.outbound_insurance.entity.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAllCountries();

    Country create (CountryDTO countryDTO);
}
