package com.hoang.controller.api.product;

import com.hoang.dto.server.ProductDto;
import com.hoang.payload.request.ProductEdit;
import com.hoang.payload.request.ProductRequest;
import com.hoang.payload.request.SkuRequest;
import com.hoang.payload.request.SkuValueRequest;
import com.hoang.projections.OptionProjections;
import com.hoang.projections.ProductProjection;
import com.hoang.projections.SkuProjection;
import com.hoang.service.ProductService;
import com.hoang.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PreAuthorize("hasAnyRole('ADMINPRODUCT','MANAGER') or hasAnyAuthority('CREATE_PRODUCT','DELETE_PRODUCT','UPDATE_PRODUCT','READ_PRODUCT')")
    @GetMapping(value = "/products")
    public ResponseEntity<Map<String,Object>> findAllProduct(
            @RequestParam(value = "page",required = false,defaultValue = "0") Integer page
    ) {
        return productService.getAll(page);
    }
    @PreAuthorize("hasAnyAuthority('UPDATE_PRODUCT','READ_PRODUCT')")
    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductDto> findOne(@PathVariable("id") Integer id) {
        return productService.findOne(id);
    }
    @PreAuthorize("hasAuthority('CREATE_PRODUCT')")
    @PostMapping(value = "/products")
    public ResponseEntity<ServiceResponse> create(@Valid @RequestBody ProductRequest productRequest, Errors errors){
        return productService.create(productRequest,errors);
    }
    @PreAuthorize("hasAuthority('UPDATE_PRODUCT')")
    @PostMapping(value = "/products/edit")
    public ResponseEntity<ServiceResponse> update(@Valid @ModelAttribute ProductEdit productEdit, Errors errors, HttpServletRequest request){
        return productService.update(productEdit,errors,request);
    }
    @PreAuthorize("hasAuthority('UPDATE_PRODUCT')")
    @GetMapping(value = "/products/{id}/skus")
    public ResponseEntity<List<SkuProjection>> skus(@PathVariable("id") Integer id){
        return productService.getSkus(id);
    }
    @PreAuthorize("hasAuthority('UPDATE_PRODUCT')")
    @PutMapping(value = "/skus")
    public ResponseEntity<ServiceResponse> edit(@RequestBody List<SkuRequest> skuRequest){
        return productService.edit(skuRequest);
    }
    @PreAuthorize("hasAuthority('UPDATE_PRODUCT')")
    @GetMapping(value = "/products/{id}/options")
    public ResponseEntity<List<OptionProjections>> getOptions(@PathVariable("id") Integer id){
        return productService.getOptions(id);
    }
    @PreAuthorize("hasAuthority('UPDATE_PRODUCT')")
    @PostMapping(value = "/skus")
    public ResponseEntity<ServiceResponse> create(@RequestBody SkuValueRequest skuValueRequest){
        return productService.create(skuValueRequest);
    }
}
