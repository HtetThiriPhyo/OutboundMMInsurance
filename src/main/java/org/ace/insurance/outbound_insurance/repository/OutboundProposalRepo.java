package org.ace.insurance.outbound_insurance.repository;

import org.ace.insurance.outbound_insurance.entity.OutboundProposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface OutboundProposalRepo extends JpaRepository <OutboundProposal,UUID>{
    List<OutboundProposal> findByInsuredPersonPassportNumberAndInsuredPersonPassportIssueCountry(String passportNo, String passportIssueCountry);
    OutboundProposal findByCertificateNo(String certificateNo);
}
