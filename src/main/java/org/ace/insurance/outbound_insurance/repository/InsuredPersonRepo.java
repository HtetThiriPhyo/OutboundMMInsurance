package org.ace.insurance.outbound_insurance.repository;

import org.ace.insurance.outbound_insurance.entity.InsuredPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsuredPersonRepo extends JpaRepository<InsuredPerson, UUID> {

}
