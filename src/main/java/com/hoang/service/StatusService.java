package com.hoang.service;

import com.hoang.convertor.StatusConvertor;
import com.hoang.global.Utility;
import com.hoang.payload.request.StatusRequest;
import com.hoang.repository.order_status.IOrderStatusRepository;
import com.hoang.repository.payment.IPaymentStatusRepository;
import com.hoang.repository.shipping.IShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService {
    @Autowired
    private IShippingRepository shippingRepository;
    @Autowired
    private IPaymentStatusRepository paymentStatusRepository;
    @Autowired
    private StatusConvertor convertor;
    @Autowired
    private IOrderStatusRepository orderStatusRepository;
    @Autowired
    private Utility utility;

    public ResponseEntity<?> getAll(String prefix){
        List<?> list = new ArrayList<>();
        switch (prefix){
            case "shipping":
                list = shippingRepository.getAll();
                break;
            case "payment":
                list = paymentStatusRepository.getAll();
                break;
            case "order_status":
                list = orderStatusRepository.findAll();
                break;
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    public ResponseEntity<ServiceResponse> create(StatusRequest statusRequest, Errors errors,String prefix) {
        ServiceResponse response = new ServiceResponse();
        try {
            if(errors.hasErrors()){
                response.setData(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
                response.setStatus("errors");
            }else if(statusRequest.getId() != null
                    ? utility.checkUnique(statusRequest.getName(), statusRequest.getId(),prefix)
                    : utility.checkUnique(statusRequest.getName(),null,prefix)){
                response.setStatus("error");
                response.setData("Tên đã tồn tại");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else{
                response.setData(statusRequest.getId() != null ? "Cập nhật thành công" : "Thêm mới thành công");
            }
            response.setStatus("success");
            switch (prefix){
                case "shipping":
                    shippingRepository.save(convertor.convertToDeliver(statusRequest));
                    break;
                case "payment":
                    paymentStatusRepository.save(convertor.convertToPayment(statusRequest));
                    break;
                case "order_status":
                    orderStatusRepository.save(convertor.convertToOrderStatus(statusRequest));
                    break;
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("error");
            response.setData("Có lỗi xảy ra");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> getOne(Integer id,String prefix) {
        Object o = new Object();
        switch (prefix){
            case "shipping":
                o = shippingRepository.findOne(id);
                break;
            case "payment":
                o = paymentStatusRepository.findOne(id);
                break;
            case "order_status":
                o = orderStatusRepository.findOne(id);
                break;
        }
        return new ResponseEntity<>(o,HttpStatus.OK);
    }
}
