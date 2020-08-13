package com.hoang.service;

import com.hoang.convertor.ProductConvertor;
import com.hoang.dto.client.OptionDto;
import com.hoang.dto.client.OptionValueDto;
import com.hoang.dto.client.ProductDetailDto;
import com.hoang.dto.server.ProductDto;
import com.hoang.entities.Product;
import com.hoang.global.Utility;
import com.hoang.payload.request.ProductEdit;
import com.hoang.payload.request.ProductRequest;
import com.hoang.payload.request.SkuRequest;
import com.hoang.payload.request.SkuValueRequest;
import com.hoang.projections.*;
import com.hoang.repository.option.IOptionRepository;
import com.hoang.repository.product.IProductRepository;
import com.hoang.repository.sku.ISkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IOptionRepository optionRepository;
    @Autowired
    private ISkuRepository skuRepository;
    @Autowired
    private ProductConvertor productConvertor;
    @Autowired
    private Utility utility;
    public ResponseEntity<Map<String,Object>> getAll(Integer page){
        Map<String,Object> map = new HashMap<>();
        Page<ProductProjection> list = productRepository.getByServer(PageRequest.of(page,5));
        map.put("totalPage",list.getTotalPages());
        map.put("listPro",list.getContent());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    public ResponseEntity<ProductDto> findOne(Integer id){
        return new ResponseEntity<>(productRepository.getOneForServer(id),HttpStatus.OK);
    }
    public String findProductSlug(String slug){
        return productRepository.uniqueSlug(slug);
    }
    public boolean checkCode(String code) { return skuRepository.uniqueCode(code) != null ? true : false; }
    public ResponseEntity<ServiceResponse> create(ProductRequest productRequest, Errors errors) {
        ServiceResponse response = new ServiceResponse<>();
        try {
            if(errors.hasErrors()){
                response.setData(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
                response.setStatus("errors");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else if(utility.checkUnique(productRequest.getName(),null,"product")||utility.checkUnique(productRequest.getSlug(),null,"product_edit")){
                response.setData("Tên hoặc đường dẫn đã tồn tại");
                response.setStatus("error");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else {
                response.setData("Thêm mới thành công");
                response.setStatus("success");
            }
            for(ProductRequest.SkuRequest skuRequest:productRequest.getSku()){
                if(checkCode(skuRequest.getCode())){
                    response.setData("Mã "+skuRequest.getCode()+" đã tồn tại");
                    response.setStatus("error");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }
            productRepository.save(productConvertor.convertor(productRequest));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setData("Có lỗi sảy ra");
            response.setStatus("error");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<ServiceResponse> update(ProductEdit productEdit, Errors errors, HttpServletRequest request){
        ServiceResponse response = new ServiceResponse<>();
        try{
            if(errors.hasErrors()){
                response.setData(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
                response.setStatus("errors");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else if(utility.checkUnique(productEdit.getName(),productEdit.getId(),"product")||utility.checkUnique(productEdit.getSlug(),productEdit.getId(),"product_edit")){
                response.setData("Tên hoặc đường dẫn đã tồn tại");
                response.setStatus("error");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            } else {
                response.setData("Cập nhật thành công");
                response.setStatus("success");
            }
            productRepository.save(productConvertor.convertor(productEdit,request));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setData("Có lỗi xảy ra");
            response.setStatus("error");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<SkuProjection>> getSkus(Integer id) {
        return new ResponseEntity<>(skuRepository.getAll(id),HttpStatus.OK);
    }

    public ResponseEntity<ServiceResponse> edit(List<SkuRequest> skuRequest) {
        ServiceResponse<String> response = new ServiceResponse<>();
        try{
            skuRepository.saveAll(productConvertor.convertSku(skuRequest));
            response.setData("Cập nhật thành công");
            response.setStatus("success");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setData("Có lỗi xảy ra");
            response.setStatus("error");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<OptionProjections>> getOptions(Integer id) {
        return new ResponseEntity<>(optionRepository.findByProduct(id),HttpStatus.OK);
    }

    public ResponseEntity<ServiceResponse> create(SkuValueRequest skuValueRequest) {
        ServiceResponse<String> response = new ServiceResponse<>();
        try {
            if(checkCode(skuValueRequest.getCode())){
                response.setData("Mã đã tồn tại");
                response.setStatus("error");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else{
                skuRepository.save(productConvertor.convertSku(skuValueRequest));
                response.setData("Thêm mới thành công");
                response.setStatus("success");
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setData("Có lỗi xảy ra");
            response.setStatus("error");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    public ProductDetailDto getDetail(String slug){
        ProductDetailDto productDetailDto = new ProductDetailDto();
        Product product = productRepository.findBySlug(slug);
        productDetailDto.setId(product.getId());
        productDetailDto.setName(product.getName());
        productDetailDto.setSlug(product.getSlug());
        productDetailDto.setVision(product.getVision());
        productDetailDto.setDiscount(product.getDiscount());
        productDetailDto.setImage(product.getImage());
        productDetailDto.setDescription(product.getDescription());
        int length = product.getImageList().split("\\,").length-1;
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= length; i++) {
            list.add(product.getImageList().split("\\,")[i]);
        }
        productDetailDto.setImage_list(list);
        Set<OptionValueDto> optionValueDtos = productRepository.findOptionValues(slug);
        Set<OptionDto> optionDtos = productRepository.findOptions(slug);
        optionDtos.forEach(optionDto -> {
            optionValueDtos.forEach(optionValueDto -> {
                if(optionValueDto.getOptionId().equals(optionDto.getId())){
                    optionDto.getOptionValueDtos().add(optionValueDto);
                }
            });
        });
        productDetailDto.setOptionDtos(optionDtos);
        return productDetailDto;
    }

    public ResponseEntity<ServiceResponse> findSku(List<Integer> integers) {
        ServiceResponse response = new ServiceResponse();
        SkuValueProjection se = productRepository.findSku(integers,integers.size());
        if(se!=null){
            response.setStatus("success");
            response.setData(se);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            response.setStatus("error");
            response.setData("Option này tạm hết hàng");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<Map<String,Object>> search(String brand,String price,String sort,Integer page,Integer size){
        Pageable pageable = null;
        Page<com.hoang.dto.client.ProductDto> productDtos = null;
        if(!sort.equals("")){
            String field = sort.substring(0,sort.indexOf("-"));
            String des = sort.substring(sort.indexOf("-")+1,sort.length());
            Sort sort1 = null;
            if(field.equals("name")){
                sort1 = Sort.by("name").ascending();
            }else if(field.equals("priority")){
                sort1 = Sort.by("priority").ascending();
            }else{
                sort1 = des.equals("asc") ? Sort.by("discount").ascending() : Sort.by("discount").descending();
            }
            pageable = PageRequest.of(page,size,sort1);
        }else{
            pageable = PageRequest.of(page,size);
        }
        if(!price.equals("")){
            Double from = Double.parseDouble(price.substring(0,price.indexOf("-")));
            Double to = Double.parseDouble(price.substring(price.indexOf("-")+1,price.length()));
            productDtos = !brand.equals("")
                    ? productRepository.filter(pageable,brand,from,to)
                    : productRepository.filter(pageable,from,to);
        }else{
            productDtos = !brand.equals("")
                    ? productRepository.filter(pageable,brand)
                    : productRepository.filter(pageable);
        }
        Map<String,Object> map = new HashMap<>();
        List<Integer> integers = new ArrayList<>();
        List<com.hoang.dto.client.ProductDto> list = null;
        for (int i = 0; i < productDtos.getTotalPages(); i++) {
            integers.add(i);
        }
        list = productDtos.getContent();
        map.put("listPages",integers);
        map.put("totalPage",productDtos.getTotalPages());
        map.put("totalItems",productDtos.getTotalElements());
        map.put("listPro",list);
        map.put("currentPage",productDtos.getNumber());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    public List<com.hoang.dto.client.ProductDto> findSpecialProduct(){
        return productRepository.findTopEight();
    }
    public List<ProductSearchProjection> searchProjections(String keyword){
        return productRepository.search(keyword,PageRequest.of(0,10)).getContent();
    }
    public List<ProductDtoProjection> getProductRelateByCategoryId(String slug){
        return productRepository.findProductByCategory(slug);
    }
}
