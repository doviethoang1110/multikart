package com.hoang.convertor;

import com.hoang.entities.Brand;
import com.hoang.payload.request.BrandRequest;
import com.hoang.repository.brand.IBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandConvertor {
    @Autowired
    private IBrandRepository brandRepository;
    public Brand convertor(BrandRequest brandRequest){
        Brand brand = new Brand();
        if(brandRequest.getId()!=0){
            brand = brandRepository.findBrandById(brandRequest.getId());
        }
        brand.setName(brandRequest.getName());
        brand.setSlug(brandRequest.getSlug());
        if(brandRequest.getImage()!=null){
            brand.setImage(brandRequest.getImage().getOriginalFilename());
        }
        brand.setStatus(brandRequest.getStatus());
        return brand;
    }
}
