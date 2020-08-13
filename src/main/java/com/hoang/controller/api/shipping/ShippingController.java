package com.hoang.controller.api.shipping;

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

public class ShippingController {
    @Autowired
    private StatusService statusService;
    @PreAuthorize("hasAnyRole('ADMINSHIPPING','MANAGER') or hasAnyAuthority('DELETE_SHIPPING','UPDATE_SHIPPING','READ_SHIPPING','CREATE_SHIPPING')")
    @GetMapping(value = "/shippings")
    public ResponseEntity<?> getAll(){
        return statusService.getAll("shipping");
    }
    @PreAuthorize("hasAuthority('UPDATE_SHIPPING')")
    @GetMapping(value = "/shippings/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id){
        return statusService.getOne(id,"shipping");
    }
    @PreAuthorize("hasAuthority('CREATE_SHIPPING')")
    @PostMapping(value = "/shippings")
    public ResponseEntity<ServiceResponse> create(@Valid @RequestBody StatusRequest shippingRequest, Errors errors){
        return statusService.create(shippingRequest,errors,"shipping");
    }
    @PreAuthorize("hasAuthority('UPDATE_SHIPPING')")
    @PutMapping(value = "/shippings")
    public ResponseEntity<ServiceResponse> update(@Valid @RequestBody StatusRequest shippingRequest,Errors errors){
        return statusService.create(shippingRequest,errors,"shipping");
    }
}
