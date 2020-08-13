package com.hoang.controller.api.order_status;

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
public class OrderStatusController {
    @Autowired
    private StatusService statusService;
    @PreAuthorize("hasAnyRole('ADMINORDER','MANAGER') or hasAnyAuthority('DELETE_ORDER','UPDATE_ORDER','READ_ORDER','CREATE_ORDER')")
    @GetMapping(value = "/order_status")
    public ResponseEntity<?> getAll(){
        return statusService.getAll("order_status");
    }
    @PreAuthorize("hasAuthority('UPDATE_ORDER')")
    @GetMapping(value = "/order_status/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id){
        return statusService.getOne(id,"order_status");
    }
    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    @PostMapping(value = "/order_status")
    public ResponseEntity<ServiceResponse> create(@Valid @RequestBody StatusRequest statusRequest, Errors errors){
        return statusService.create(statusRequest,errors,"order_status");
    }
    @PreAuthorize("hasAuthority('UPDATE_ORDER')")
    @PutMapping(value = "/order_status")
    public ResponseEntity<ServiceResponse> update(@Valid @RequestBody StatusRequest statusRequest,Errors errors){
        return statusService.create(statusRequest,errors,"order_status");
    }
}
