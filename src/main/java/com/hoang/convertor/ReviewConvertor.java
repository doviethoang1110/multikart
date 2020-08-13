package com.hoang.convertor;

import com.hoang.dto.server.ReviewDto;
import com.hoang.entities.Product;
import com.hoang.entities.Review;
import com.hoang.repository.product.IProductRepository;
import com.hoang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewConvertor {
    @Autowired
    private IProductRepository productRepository;
    public Review convertor(ReviewDto reviewDto){
        Review review = new Review();
        if(reviewDto.getId()!=null){
            review.setId(reviewDto.getId());
        }
        review.setName(reviewDto.getName());
        review.setContent(reviewDto.getContent());
        review.setStatus(false);
        review.setEmail(reviewDto.getEmail());
        review.setRating(reviewDto.getRating());
        review.setProduct(productRepository.findBySlug(reviewDto.getSlug()));
        return review;
    }
}
