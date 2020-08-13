package com.hoang.convertor;

import com.hoang.entities.Option;
import com.hoang.entities.OptionValue;
import com.hoang.entities.Product;
import com.hoang.entities.Sku;
import com.hoang.payload.request.ProductEdit;
import com.hoang.payload.request.ProductRequest;
import com.hoang.payload.request.SkuRequest;
import com.hoang.payload.request.SkuValueRequest;
import com.hoang.repository.brand.IBrandRepository;
import com.hoang.repository.category.ICategoryRepository;
import com.hoang.repository.option.IOptionRepository;
import com.hoang.repository.option_value.IOptionValueRepository;
import com.hoang.repository.product.IProductRepository;
import com.hoang.repository.sku.ISkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class ProductConvertor {
    @Autowired
    private IBrandRepository brandRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ISkuRepository skuRepository;
    @Autowired
    private IOptionRepository optionRepository;
    public Product convertor(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setSlug(productRequest.getSlug());
        product.setBrand(brandRepository.findBrandById(productRequest.getBrand()));
        product.setCategory(categoryRepository.findById(productRequest.getCategory()).get());
        product.setDescription(productRequest.getDescription());
        product.setDiscount(productRequest.getDiscount());
        product.setPriority(productRequest.getPriority());
        product.setVision(productRequest.getVision());
        product.setStatus(productRequest.getStatus());
        product.setImage("");
        product.setImageList("");
        Set<Option> set = new HashSet<>();
        Set<OptionValue> optionValues = new HashSet<>();
        productRequest.getOption().forEach(optionRequest -> {
            Option option = new Option();
            option.setName(optionRequest.getOption());
            option.setProduct(product);
            optionRequest.getValues().forEach(op->{
                OptionValue optionValue = new OptionValue();
                optionValue.setName(op);
                optionValue.setOption(option);
                option.getOptionValues().add(optionValue);
                optionValues.add(optionValue);
            });
            set.add(option);
        });
        product.setOptions(set);
        Set<Sku> skus = new HashSet<>();
        productRequest.getSku().forEach(skuRequest -> {
            Sku sku = new Sku();
            sku.setStock(skuRequest.getStock());
            sku.setCode(skuRequest.getCode());
            sku.setImportPrice(skuRequest.getImportPrice());
            sku.setExportPrice(skuRequest.getExportPrice());
            sku.setProduct(product);
            sku.setStatus("1");
            sku.setQuantity(0);
            skus.add(sku);
            skuRequest.getValues().forEach(skuValue -> {
                for (OptionValue optionValue:optionValues){
                    if(optionValue.getName().equals(skuValue.getValue())){
                        sku.getOptionValues().add(optionValue);
                        optionValue.getSkus().add(sku);
                    }else{
                        continue;
                    }
                }
            });
        });
        product.setSkus(skus);
        return productRepository.save(product);
    }
    public Product convertor(ProductEdit productEdit, HttpServletRequest request){
        Product product = productRepository.getById(productEdit.getId());
        product.setName(productEdit.getName());
        product.setSlug(productEdit.getSlug());
        product.setBrand(brandRepository.findBrandById(productEdit.getBrand()));
        product.setCategory(categoryRepository.findById(productEdit.getCategory()).get());
        product.setDescription(productEdit.getDescription());
        product.setDiscount(productEdit.getDiscount());
        product.setPriority(productEdit.getPriority());
        product.setVision(productEdit.getVision());
        product.setStatus(productEdit.getStatus());
        if(productEdit.getImage()!=null){
            product.setImage(productEdit.getImage().getOriginalFilename());
            File file = new File(request.getServletContext().getRealPath("WEB-INF/resources/user/assets/images/pro3/")+productEdit.getImage().getOriginalFilename());
            if(!file.exists()){
                file.mkdirs();
            }
            try {
                productEdit.getImage().transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(productEdit.getImage_list()!=null){
            if(productEdit.getImage_list().length>0){
                String a = "";
                for (int i =0;i<productEdit.getImage_list().length;i++){
                    a += productEdit.getImage_list()[i].getOriginalFilename()+",";
                    File file = new File(request.getServletContext().getRealPath("WEB-INF/resources/user/assets/images/pro3/")+productEdit.getImage_list()[i].getOriginalFilename());
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    try {
                        productEdit.getImage_list()[i].transferTo(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                product.setImageList(a);
            }
        }
        return product;
    }
    public List<Sku> convertSku(List<SkuRequest> skuRequest){
        List<Sku> skus = new ArrayList<>();
        skuRequest.forEach(skuRequest1 -> {
            Sku sku = skuRepository.findOne(skuRequest1.getId());
            sku.setStock(skuRequest1.getStock());
            sku.setImportPrice(skuRequest1.getImportPrice());
            sku.setExportPrice(skuRequest1.getExportPrice());
            skus.add(sku);
        });
        return skus;
    }
    public Sku convertSku(SkuValueRequest skuValueRequest){
        Sku sku = new Sku();
        sku.setCode(skuValueRequest.getCode());
        sku.setImportPrice(skuValueRequest.getImportPrice());
        sku.setExportPrice(skuValueRequest.getExportPrice());
        sku.setStock(skuValueRequest.getStock());
        sku.setStatus("1");
        sku.setQuantity(0);
        sku.setProduct(productRepository.getById(skuValueRequest.getProductId()));
        sku.getOptionValues().addAll(skuValueRequest.getOptions().stream().map(e->{
            OptionValue optionValue = new OptionValue();
            optionValue.setOption(optionRepository.findOne(e.getId()));
            optionValue.setName(e.getValue());
            optionValue.getSkus().add(sku);
            return optionValue;
        }).collect(Collectors.toSet()));
        return sku;
    }
}
