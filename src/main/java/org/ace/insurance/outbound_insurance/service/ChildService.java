package org.ace.insurance.outbound_insurance.service;

import org.ace.insurance.outbound_insurance.dto.ChildDTO;
import org.ace.insurance.outbound_insurance.entity.Child;

public interface ChildService {

    Child create(ChildDTO childDTO);
}
