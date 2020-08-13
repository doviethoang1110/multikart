package com.hoang.service;

import com.hoang.entities.Currency;
import com.hoang.global.Utility;
import com.hoang.repository.currency.ICurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    @Autowired
    private ICurrencyRepository currencyRepository;
    @Autowired
    private Utility utility;
    public ResponseEntity<List<Currency>> findAll() {
        return new ResponseEntity<>(currencyRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<ServiceResponse> create(Currency currency, Errors errors) {
        ServiceResponse response = new ServiceResponse();
        try {
            if(errors.hasErrors()){
                response.setData(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
                response.setStatus("errors");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else if(currency.getId() !=null
                ? utility.checkUnique(currency.getCode(),currency.getId(),"currency")
                : utility.checkUnique(currency.getCode(),null,"currency")){
                response.setStatus("error");
                response.setData("Mã đã tồn tại");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else{
                response.setData(currency.getId() != null
                    ? "Cập nhật thành công" : "Thêm mới thành công");
            }
            response.setStatus("success");
            currencyRepository.save(currency);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("error");
            response.setData("Có lỗi xảy ra");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Currency> findOne(Integer id) {
        return new ResponseEntity<>(currencyRepository.findById(id).get(),HttpStatus.OK);
    }
}
