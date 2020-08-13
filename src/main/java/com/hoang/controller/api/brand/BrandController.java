package com.hoang.controller.api.brand;

import com.hoang.dto.client.BrandDto;
import com.hoang.entities.Brand;
import com.hoang.entities.Category;
import com.hoang.payload.request.BrandRequest;
import com.hoang.service.BrandService;
import com.hoang.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@Controller
@RequestMapping(value = "/api/v1")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @GetMapping(value = "brands")
    @PreAuthorize("hasAnyRole('MANAGER','ADMINBRAND') or hasAnyAuthority('CREATE_BRAND','DELETE_BRAND','UPDATE_BRAND','READ_BRAND')")
    public ResponseEntity<Map<String,Object>> getAll(@RequestParam(value = "page",required = false,defaultValue = "0") Integer page){
        return brandService.findAllForServer(page);
    }
    @GetMapping(value = "brands/getAll")
    @PreAuthorize("hasAnyRole('MANAGER','ADMINBRAND') or hasAnyAuthority('CREATE_BRAND','DELETE_BRAND','UPDATE_BRAND','READ_BRAND')")
    public ResponseEntity<List<Brand>> findAll(){
        return brandService.getAll();
    }
    @PostMapping(value = "brands")
    @PreAuthorize("hasAnyAuthority('CREATE_BRAND','UPDATE_BRAND')")
    public ResponseEntity<ServiceResponse> create(@Valid @ModelAttribute BrandRequest brandRequest, Errors errors,HttpServletRequest request){
        return brandService.createBrand(brandRequest,errors,request);
    }
    @GetMapping(value = "brands/{id}")
    @PreAuthorize("hasAuthority('READ_BRAND')")
    public ResponseEntity<Brand> getById(@PathVariable("id") Integer id){
        return brandService.findById(id);
    }

    @PreAuthorize("hasAuthority('DELETE_BRAND')")
    @DeleteMapping(value = "brands/{id}")
    public ResponseEntity<ServiceResponse> delete(@PathVariable("id") Integer id){
        return brandService.delete(id);
    }
}
