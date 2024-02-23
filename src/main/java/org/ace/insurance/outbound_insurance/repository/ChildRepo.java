package org.ace.insurance.outbound_insurance.repository;

import org.ace.insurance.outbound_insurance.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChildRepo extends JpaRepository<Child, UUID> {
}
