package org.ace.insurance.outbound_insurance.service;

import org.ace.insurance.outbound_insurance.dto.BeneficiaryDTO;
import org.ace.insurance.outbound_insurance.entity.Beneficiary;

import java.util.List;

public interface BeneficiaryService {
    List<Beneficiary>findAllBeneficiaries();

    Beneficiary create (BeneficiaryDTO beneficiaryDTO);
}
