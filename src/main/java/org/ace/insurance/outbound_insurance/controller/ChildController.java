package org.ace.insurance.outbound_insurance.controller;

import lombok.AllArgsConstructor;
import org.ace.insurance.outbound_insurance.dto.ChildDTO;
import org.ace.insurance.outbound_insurance.entity.Child;
import org.ace.insurance.outbound_insurance.service.ChildService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utility.HttpResponse;
import static utility.HttpResponse.createResponse;

@RestController
@RequestMapping("/child")
@AllArgsConstructor
public class ChildController {

    private final ChildService childService;

    @PostMapping
    private ResponseEntity<HttpResponse<Child>>createChild(@RequestBody ChildDTO childDTO){
        Child child = childService.create(childDTO);
        return createResponse(child,HttpStatus.CREATED);
    }
}
