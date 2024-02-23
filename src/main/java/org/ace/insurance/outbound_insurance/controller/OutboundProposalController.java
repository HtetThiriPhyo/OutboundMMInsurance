package org.ace.insurance.outbound_insurance.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ace.insurance.outbound_insurance.dto.OutboundProposalDTO;
import org.ace.insurance.outbound_insurance.entity.OutboundProposal;
import org.ace.insurance.outbound_insurance.service.OutboundProposalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utility.HttpResponse;

import java.util.List;

import static utility.HttpResponse.createResponse;

@RestController
@RequestMapping("/outboundProposal")
@Slf4j
@AllArgsConstructor
public class OutboundProposalController {
    private final OutboundProposalService outboundProposalService;

    @PostMapping
    public ResponseEntity<HttpResponse<OutboundProposal>>create(@RequestBody OutboundProposalDTO outboundProposalDTO){
        OutboundProposal outboundProposal = outboundProposalService.create(outboundProposalDTO);
        return createResponse(outboundProposal, HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<HttpResponse<List<OutboundProposal>>>searchEnquiry(@RequestParam String passportNo,
                                                                        @RequestParam String passportIssueCountry){
        List<OutboundProposal> outboundProposal = outboundProposalService.searchEnquiry(passportNo,passportIssueCountry);
        return createResponse(outboundProposal,HttpStatus.OK);
    }

    @GetMapping("/findByCertificateNo")
    public  ResponseEntity<HttpResponse<OutboundProposal>>findByCertificateNo(@RequestParam("id") String certificateNo){
        OutboundProposal outboundProposal = outboundProposalService.findByCertificateNo(certificateNo);
        return createResponse(outboundProposal,HttpStatus.OK);
    }
}
