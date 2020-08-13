package com.hoang.service;

import com.hoang.convertor.CategoryConvertor;
import com.hoang.dto.client.CategoryDto;
import com.hoang.dto.client.CategoryTree;
import com.hoang.entities.Brand;
import com.hoang.entities.Category;
import com.hoang.global.GlobalModel;
import com.hoang.global.Utility;
import com.hoang.payload.request.BrandRequest;
import com.hoang.payload.request.CategoryRequest;
import com.hoang.projections.CategoryProjection;
import com.hoang.projections.ProductDtoProjection;
import com.hoang.repository.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private CategoryConvertor convertor;
    @Autowired
    private Utility utility;
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    public String findCategorySlug(String slug){
        return categoryRepository.findSlug(slug);
    }
    public List<CategoryDto> getAllForClient(){
        return categoryRepository.findAllDto();
    }
    public ResponseEntity<Category> findById(Integer id){
        return new ResponseEntity<>(categoryRepository.findById(id).get(),HttpStatus.OK);
    }

    public List<CategoryTree> treeView(Integer id){
        List<CategoryDto> list = categoryRepository.findByParentId(id);
        List<CategoryTree> output = new ArrayList<>();
        for (CategoryDto category:list){
            List<CategoryTree> subTree = new ArrayList<>();
            CategoryTree categoryTree = new CategoryTree();
            categoryTree.setId(category.getId());
            categoryTree.setLabel(category.getName());
            categoryTree.setChildren(treeView(category.getId()));
            subTree.add(categoryTree);
            output.addAll(subTree);
        }
        return output;
    }

    public ResponseEntity<ServiceResponse> create(CategoryRequest categoryRequest, Errors errors){
        ServiceResponse response = new ServiceResponse<>();
        try {
            if(errors.hasErrors()){
                response.setStatus("errors");
                response.setData(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else if(categoryRequest.getId() != null
                    ? utility.checkUnique(categoryRequest.getSlug(),categoryRequest.getId(),"category")
                    : utility.checkUnique(categoryRequest.getSlug(),null,"category")){
                response.setData("Đường dẫn đã tồn tại");
                response.setStatus("errors");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else{
                response.setData(categoryRequest.getId() != null
                        ? "Cập nhật thành công"
                        : "Thêm mới thành công");
            }
            response.setStatus("success");
            categoryRepository.save(convertor.convertor(categoryRequest));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setData("Có lỗi sảy ra");
            response.setStatus("error");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ServiceResponse> delete(Integer id) {
        ServiceResponse response = new ServiceResponse<>();
        if(categoryRepository.findProduct(id).size()>0){
            response.setData("Danh mục chứa sản phẩm không thể xóa");
        }else if(categoryRepository.findCategories(id).size()>0){
            response.setData("Danh mục chứa danh mục con không thể xóa");
        }else{
            categoryRepository.deleteById(id);
            response.setData("Xóa thành công");
        }
        response.setStatus("success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    public List<ProductDtoProjection> findProductByCategory(String slug){
        List<ProductDtoProjection> list = categoryRepository.findProductByCategory(slug);
        return list;
    }
}
