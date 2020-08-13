package com.hoang.convertor;

import com.hoang.entities.Category;
import com.hoang.payload.request.CategoryRequest;
import org.springframework.stereotype.Component;

@Component
public class CategoryConvertor {
    public Category convertor(CategoryRequest categoryRequest){
        Category category = new Category();
        if(categoryRequest.getId()!=null){
            category.setId(categoryRequest.getId());
        }
        category.setName(categoryRequest.getName());
        category.setSlug(categoryRequest.getSlug());
        category.setParentId(categoryRequest.getParentId());
        category.setStatus(categoryRequest.getStatus());
        return category;
    }
}
