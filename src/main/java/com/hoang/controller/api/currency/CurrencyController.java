package com.hoang.controller.api.currency;

import com.hoang.entities.Currency;
import com.hoang.service.CurrencyService;
import com.hoang.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(value = "/currencies")
    public ResponseEntity<List<Currency>> findAll(){
        return currencyService.findAll();
    }

    @PostMapping(value = "/currencies")
    public ResponseEntity<ServiceResponse> add(@Valid  @RequestBody Currency currency, Errors errors){
        return currencyService.create(currency,errors);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(value = "currencies/{id}")
    public ResponseEntity<Currency> findOne(@PathVariable("id") Integer id){
        return currencyService.findOne(id);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping(value = "/currencies")
    public ResponseEntity<ServiceResponse> edit(@Valid @RequestBody Currency currency,Errors errors){
        return currencyService.create(currency,errors);
    }
}
