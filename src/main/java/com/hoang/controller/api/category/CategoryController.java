package com.hoang.controller.api.category;

import com.hoang.dto.client.CategoryTree;
import com.hoang.entities.Category;
import com.hoang.payload.request.CategoryRequest;
import com.hoang.service.CategoryService;
import com.hoang.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping(value = "/categories")
    @PreAuthorize("hasAnyRole('ADMINCATEGORY','MANAGER') or hasAnyAuthority('CREATE_CATEGORY','DELETE_CATEGORY','UPDATE_CATEGORY','READ_CATEGORY')")
    public ResponseEntity<List<CategoryTree>> treeView(){
        return new ResponseEntity<>(categoryService.treeView(0),HttpStatus.OK);
    }
    @GetMapping(value = "categories/{id}")
    @PreAuthorize("hasAuthority('READ_CATEGORY')")
    public ResponseEntity<Category> getById(@PathVariable("id") Integer id){
        return categoryService.findById(id);
    }
    @PostMapping(value = "/categories")
    @PreAuthorize("hasAuthority('CREATE_CATEGORY')")
    public ResponseEntity<ServiceResponse> create(@Valid @RequestBody CategoryRequest categoryRequest, Errors errors){
        return categoryService.create(categoryRequest,errors);
    }
    @PreAuthorize("hasAuthority('UPDATE_CATEGORY')")
    @PutMapping(value = "categories")
    public ResponseEntity<ServiceResponse> update(@Valid @RequestBody CategoryRequest categoryRequest,Errors errors){
        return categoryService.create(categoryRequest,errors);
    }
    @PreAuthorize("hasAuthority('DELETE_CATEGORY')")
    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<ServiceResponse> delete(@PathVariable("id") Integer id){
        return categoryService.delete(id);
    }
}
