package org.ace.insurance.outbound_insurance.service;

import org.ace.insurance.outbound_insurance.dto.InsuredPersonDTO;
import org.ace.insurance.outbound_insurance.entity.InsuredPerson;

public interface InsuredPersonService {
   InsuredPerson create(InsuredPersonDTO insuredPersonDTO);
}
