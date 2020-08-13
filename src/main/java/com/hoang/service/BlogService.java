package com.hoang.service;

import com.hoang.convertor.BlogConvertor;
import com.hoang.entities.Blog;
import com.hoang.global.GlobalModel;
import com.hoang.payload.request.BlogRequest;
import com.hoang.projections.BlogProjection;
import com.hoang.repository.blog.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BlogService {
    @Autowired
    private IBlogRepository blogRepository;
    @Autowired
    private BlogConvertor convertor;
    public ResponseEntity<Map<String,Object>> findAll(Integer page){
        Map<String,Object> map = new HashMap<>();
        Page<Blog> blogs = blogRepository.findAllPaginate(PageRequest.of(page,4));
        map.put("totalPage",blogs.getTotalPages());
        map.put("listBlogs",blogs.getContent());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    public ResponseEntity<ServiceResponse> create(BlogRequest request, Errors errors, HttpServletRequest servletRequest){
        ServiceResponse response = new ServiceResponse();
        try {
            if(errors.hasErrors()){
                response.setStatus("errors");
                response.setData(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else{
                if(request.getImage()!=null){
                    GlobalModel.upload(request.getImage(),servletRequest.getServletContext().getRealPath("WEB-INF/resources/user/assets/images/blog/")+request.getImage().getOriginalFilename());
                }
                response.setData(request.getId().equals(0) ? "Thêm mới thành công" : "Cập nhật thành công");
                response.setStatus("success");
                blogRepository.save(convertor.convertor(request));
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setData("Có lỗi xảy ra");
            response.setStatus("errors");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<ServiceResponse> remove(Integer id){
        ServiceResponse response = new ServiceResponse();
        try {
            blogRepository.deleteById(id);
            response.setStatus("success");
            response.setData("Xóa thành công");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setData("Có lỗi xảy ra");
            response.setStatus("errors");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Blog> findOne(Integer id) {
        return new ResponseEntity<>(blogRepository.findById(id).get(),HttpStatus.OK);
    }

    public ResponseEntity<ServiceResponse> upload(MultipartFile image, HttpServletRequest request) {
        ServiceResponse response = new ServiceResponse();
        GlobalModel.upload(image,request.getServletContext().getRealPath("WEB-INF/resources/user/assets/images/uploads/")+image.getOriginalFilename());
        response.setData("http://multikart.j.layershift.co.uk/resources/images/uploads/"+image.getOriginalFilename());
        response.setStatus("success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> pageBlog(Integer page) {
        Map<String,Object> map = new HashMap<>();
        Page<BlogProjection> blogProjections = blogRepository.getAll(PageRequest.of(page,1));
        map.put("listBlog",blogProjections.getContent());
        map.put("totalPages",blogProjections.getTotalPages());
        map.put("currentPage",blogProjections.getNumber());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    public BlogProjection findBySlug(String slug){
        return blogRepository.findBySlug(slug);
    }
    public List<BlogProjection> findListRecent(){
        return blogRepository.findRecent();
    }
}
