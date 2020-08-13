package com.hoang.controller.api.payment;

import com.hoang.payload.request.StatusRequest;
import com.hoang.service.ServiceResponse;
import com.hoang.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RequestMapping(value = "/api/v1")
public class PaymentController {
    @Autowired
    private StatusService statusService;
    @PreAuthorize("hasAnyRole('ADMINPAYMENT','MANAGER') or hasAnyAuthority('DELETE_PAYMENT','UPDATE_PAYMENT','READ_PAYMENT','CREATE_PAYMENT')")
    @GetMapping(value = "/payments")
    public ResponseEntity<?> getAll(){
        return statusService.getAll("payment");
    }
    @PreAuthorize("hasAuthority('UPDATE_PAYMENT')")
    @GetMapping(value = "/payments/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id){
        return statusService.getOne(id,"payment");
    }
    @PreAuthorize("hasAuthority('CREATE_PAYMENT')")
    @PostMapping(value = "/payments")
    public ResponseEntity<ServiceResponse> create(@Valid @RequestBody StatusRequest statusRequest, Errors errors){
        return statusService.create(statusRequest,errors,"payment");
    }
    @PreAuthorize("hasAuthority('UPDATE_PAYMENT')")
    @PutMapping(value = "/payments")
    public ResponseEntity<ServiceResponse> update(@Valid @RequestBody StatusRequest statusRequest, Errors errors){
        return statusService.create(statusRequest,errors,"payment");
    }
}
