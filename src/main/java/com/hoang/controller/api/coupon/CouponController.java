package com.hoang.controller.api.coupon;

import com.hoang.dto.server.CouponDto;
import com.hoang.dto.server.CustomerDto;
import com.hoang.payload.request.CouponCustomerRequest;
import com.hoang.payload.request.CouponRequest;
import com.hoang.projections.CouponProjection;
import com.hoang.service.CouponService;
import com.hoang.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @PreAuthorize("hasAnyAuthority('READ_COUPON','CREATE_COUPON','UPDATE_COUPON','DELETE_COUPON') or hasAnyRole('ADMINCATEGORY','MANAGER')")
    @GetMapping(value = "coupons")
    public ResponseEntity<List<CouponProjection>> getAll(){
        return couponService.getAll();
    }

    @PreAuthorize("hasAuthority('CREATE_COUPON')")
    @PostMapping(value = "coupons")
    public ResponseEntity<ServiceResponse> create(@Valid @RequestBody CouponRequest couponRequest, Errors errors){
        return couponService.create(couponRequest,errors);
    }
    @PreAuthorize("hasAuthority('CREATE_COUPON')")
    @GetMapping(value = "users")
    public ResponseEntity<List<CustomerDto>> getUser(){
        return couponService.getUsers();
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<CouponDto> getUser(@PathVariable("id") Integer id){
        return couponService.getUser(id);
    }

    @PreAuthorize("hasAuthority('CREATE_COUPON')")
    @PostMapping(value = "coupons/deliver")
    public ResponseEntity<ServiceResponse> deliver(@RequestBody CouponCustomerRequest couponCustomerRequest){
        return couponService.deliver(couponCustomerRequest);
    }

    @PreAuthorize("hasAuthority('UPDATE_COUPON')")
    @GetMapping(value = "coupons/{id}")
    public ResponseEntity<CouponProjection> edit(@PathVariable("id") Integer id){
        return couponService.edit(id);
    }

    @PreAuthorize("hasAuthority('UPDATE_COUPON')")
    @PutMapping(value = "coupons")
    public ResponseEntity<ServiceResponse> update(@Valid @RequestBody CouponRequest couponRequest,Errors errors){
        return couponService.create(couponRequest,errors);
    }
}
