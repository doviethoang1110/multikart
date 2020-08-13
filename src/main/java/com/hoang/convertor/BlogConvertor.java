package com.hoang.convertor;

import com.hoang.entities.Blog;
import com.hoang.payload.request.BlogRequest;
import com.hoang.repository.blog.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogConvertor {
    @Autowired
    private IBlogRepository blogRepository;
    public Blog convertor(BlogRequest request){
        Blog blog = new Blog();
        if(!request.getId().equals(0)){
            blog = blogRepository.findById(request.getId()).get();
        }
        blog.setTitle(request.getTitle());
        blog.setContent(request.getContent());
        blog.setStatus(request.getStatus());
        blog.setSlug(request.getSlug());
        if(request.getImage()!=null){
            blog.setImage(request.getImage().getOriginalFilename());
        }
        return blog;
    }
}
