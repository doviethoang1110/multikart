package com.hoang.service;

import com.hoang.convertor.CustomerConvertor;
import com.hoang.entities.Customer;
import com.hoang.entities.VerificationToken;
import com.hoang.event.RegisterCompleteEvent;
import com.hoang.payload.request.CustomerRequest;
import com.hoang.repository.customer.ICustomerRepository;
import com.hoang.repository.verify_token.IVerifyTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerService{
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private CustomerConvertor customerConvertor;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private IVerifyTokenRepository verifyTokenRepository;
    public ResponseEntity<ServiceResponse> register(CustomerRequest customer, Errors errors,String url){
        ServiceResponse response = new ServiceResponse();
        try {
            if(errors.hasErrors()){
                response.setStatus("error");
                response.setData(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
                response.setStatus("errors");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else if(customerRepository.checkName(customer.getName())!=null){
                response.setData("Tên đã tồn tại");
                response.setStatus("errors");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else if(customerRepository.checkEmail(customer.getEmail())!=null){
                response.setData("Email này đã tồn tại");
                response.setStatus("errors");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else if(customerRepository.checkPhone(customer.getPhone())!=null){
                response.setData("Số điện thoại này đã tồn tại");
                response.setStatus("errors");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else{
                Customer customer1 = customerConvertor.convertor(customer);
                customerRepository.save(customer1);
                applicationEventPublisher.publishEvent(new RegisterCompleteEvent(customer1,url));
                response.setData("Đăng ký thành công");
                response.setStatus("success");
                return new ResponseEntity<>(response,HttpStatus.CREATED);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setData("Có lỗi xảy ra");
            response.setStatus("errors");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    public Map<String,String> verifyEmail(String token){
        Map<String,String> map = new HashMap<>();
        VerificationToken verificationToken = verifyTokenRepository.findByToken(token);
        if(verificationToken==null){
            map.put("error","Token này không hợp lệ");
        }else if(verificationToken.getExpireDate().isBefore(LocalDateTime.now())){
            map.put("error","Token này đã hết hạn");
        }else{
            Customer customer = verificationToken.getCustomer();
            customer.setEnabled(true);
            customerRepository.save(customer);
            map.put("success","Kích hoạt tài khoản thành công");
        }
        return map;
    }
}
