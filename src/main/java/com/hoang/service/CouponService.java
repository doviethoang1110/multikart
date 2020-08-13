package com.hoang.service;

import com.hoang.convertor.CouponConvertor;
import com.hoang.dto.server.CouponDto;
import com.hoang.dto.server.CustomerDto;
import com.hoang.entities.Coupon;
import com.hoang.entities.Customer;
import com.hoang.entities.Permissions;
import com.hoang.global.Utility;
import com.hoang.payload.request.CouponCustomerRequest;
import com.hoang.payload.request.CouponRequest;
import com.hoang.projections.CouponDtoProjection;
import com.hoang.projections.CouponProjection;
import com.hoang.repository.coupon.ICouponRepository;
import com.hoang.repository.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CouponService {
    @Autowired
    private ICouponRepository couponRepository;
    @Autowired
    private CouponConvertor convertor;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private Utility utility;
    public ResponseEntity<List<CouponProjection>> getAll(){
        return new ResponseEntity<>(couponRepository.getAll(),HttpStatus.OK);
    }
    public ResponseEntity<ServiceResponse> create(CouponRequest couponRequest, Errors errors){
        ServiceResponse response = new ServiceResponse();
        if(errors.hasErrors()){
            response.setData(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
            response.setStatus("error");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }else if(couponRequest.getId() != null
            ? utility.checkUnique(couponRequest.getCode(),couponRequest.getId(),"coupon")
            : utility.checkUnique(couponRequest.getCode(),null,"coupon")){
            response.setData("Mã đã tồn tại");
            response.setStatus("error");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }else{
            if(couponRequest.getId() != null){
                Coupon coupon = convertor.convertor(couponRequest);
                if(couponRequest.getEndDate().after(couponRepository.find(couponRequest.getId()).getEndDate())&&coupon.getStatus()==1){
                    coupon.setStatus(Short.parseShort("0"));
                }
                couponRepository.save(coupon);
                response.setData("Cập nhật thành công");
            }else{
                couponRepository.save(convertor.convertor(couponRequest));
                response.setData("Thêm mới thành công");
            }
            response.setStatus("success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<List<CustomerDto>> getUsers() {
        return new ResponseEntity<>(couponRepository.getUsers(),HttpStatus.OK);
    }

    public ResponseEntity<ServiceResponse> deliver(CouponCustomerRequest couponCustomerRequest) {
        ServiceResponse response = new ServiceResponse();
        try{
            for(Integer integer:couponCustomerRequest.getCustomer_id()){
                if(customerRepository.findCustomers(integer,couponCustomerRequest.getCoupon_id())>0){
                    response.setData(customerRepository.findOne(integer).getName()+" đã được phát coupon này");
                    response.setStatus("error");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }
            Coupon coupon = couponRepository.findOne(couponCustomerRequest.getCoupon_id());
            coupon.getCustomers().addAll(couponCustomerRequest.getCustomer_id().stream().map(request -> {
                Customer customer = customerRepository.findOne(request);
                customer.getCoupons().add(coupon);
                return customer;
            }).collect(Collectors.toList()));
            couponRepository.save(coupon);
            response.setData("Phát coupon thành công");
            response.setStatus("success");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setData("Có lỗi xảy ra");
            response.setStatus("error");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<CouponDto> getUser(Integer id) {
        CouponDto couponDto = new CouponDto();
        try{
            Customer customer = customerRepository.findOne(id);
            couponDto.setName(customer.getName());
            couponDto.setCoupons(customer.getCoupons());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(couponDto,HttpStatus.OK);
    }

    public ResponseEntity<CouponProjection> edit(Integer id) {
        return new ResponseEntity<>(couponRepository.find(id),HttpStatus.OK);
    }

    public ResponseEntity<CouponDtoProjection> getCoupon(String coupon, Integer id) {
        return new ResponseEntity<>(couponRepository.getCoupon(coupon,id),HttpStatus.OK);
    }
    public ResponseEntity<List<CouponDtoProjection>> getCoupons(Integer id){
        return new ResponseEntity<>(couponRepository.getCoupons(id),HttpStatus.OK);
    }
}
