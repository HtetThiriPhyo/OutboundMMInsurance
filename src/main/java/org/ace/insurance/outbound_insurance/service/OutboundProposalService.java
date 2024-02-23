package org.ace.insurance.outbound_insurance.service;

import org.ace.insurance.outbound_insurance.dto.OutboundProposalDTO;
import org.ace.insurance.outbound_insurance.entity.OutboundProposal;

import java.util.List;

public interface OutboundProposalService {
    OutboundProposal create(OutboundProposalDTO outboundProposalDTO);

    List<OutboundProposal> searchEnquiry(String passportNo, String passportIssueCountry);

    OutboundProposal findByCertificateNo(String certificateNo);
}
