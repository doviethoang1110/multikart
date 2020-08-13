package com.hoang.controller.api.blog;

import com.hoang.entities.Blog;
import com.hoang.global.GlobalModel;
import com.hoang.payload.request.BlogRequest;
import com.hoang.service.BlogService;
import com.hoang.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@Controller
@RequestMapping(value = "/api/v1")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PreAuthorize("hasAuthority('READ_BLOG')")
    @GetMapping(value = "blogs")
    public ResponseEntity<Map<String,Object>> findAll(@RequestParam(value = "page",required = false,defaultValue = "0") Integer page){
        return blogService.findAll(page);
    }

    @PreAuthorize("hasAuthority('READ_BLOG')")
    @GetMapping(value = "blogs/{id}")
    public ResponseEntity<Blog> findById(@PathVariable("id") Integer id){
        return blogService.findOne(id);
    }

    @PreAuthorize("hasAnyAuthority('CREATE_BLOG','UPDATE_BLOG')")
    @PostMapping(value = "blogs")
    public ResponseEntity<ServiceResponse> create(@Valid @ModelAttribute BlogRequest request, Errors errors,HttpServletRequest servletRequest){
        return blogService.create(request,errors,servletRequest);
    }

    @PreAuthorize("hasAuthority('DELETE_BLOG')")
    @DeleteMapping(value = "blogs/{id}")
    public ResponseEntity<ServiceResponse> delete(@PathVariable("id") Integer id){
        return blogService.remove(id);
    }

    @PreAuthorize("hasAnyAuthority('CREATE_BLOG','UPDATE_BLOG','CREATE_PRODUCT','UPDATE_PRODUCT')")
    @PostMapping(value = "/editor/file")
    public ResponseEntity<ServiceResponse> restApi(@ModelAttribute MultipartFile image,HttpServletRequest request){
        return blogService.upload(image,request);
    }
}
