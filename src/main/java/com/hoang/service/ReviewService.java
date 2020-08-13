package com.hoang.service;

import com.hoang.convertor.ReviewConvertor;
import com.hoang.dto.server.ReviewDto;
import com.hoang.entities.Review;
import com.hoang.projections.ReviewProjection;
import com.hoang.repository.review.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {
    @Autowired
    private ReviewConvertor convertor;
    @Autowired
    private IReviewRepository reviewRepository;
    public ResponseEntity<ServiceResponse> create(ReviewDto reviewDto) {
        ServiceResponse response = new ServiceResponse();
        try {
            reviewRepository.save(convertor.convertor(reviewDto));
            response.setData("Thêm đánh giá thành công");
            response.setStatus("success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("error");
            response.setData("Có lỗi xảy ra");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String,Object>> findAll(Integer page) {
        Map<String,Object> map = new HashMap<>();
        Page<Review> reviews = reviewRepository.findAll(PageRequest.of(page,5));
        map.put("totalPage",reviews.getTotalPages());
        map.put("listReviews",reviews.getContent());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    public ResponseEntity<ServiceResponse> update(Map<String, Object> map) {
        ServiceResponse response = new ServiceResponse();
        try{
            Review review = reviewRepository.findById(Integer.parseInt(map.get("id").toString())).get();
            review.setStatus(Boolean.parseBoolean(map.get("status").toString()));
            reviewRepository.save(review);
            response.setStatus("success");
            response.setData("Cập nhật thành công");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("error");
            response.setData("Có lỗi sảy ra");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ServiceResponse> delete(Integer id) {
        ServiceResponse response = new ServiceResponse();
        reviewRepository.deleteById(id);
        response.setStatus("success");
        response.setData("Xóa thành công");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ServiceResponse> multidelete(List<Integer> integers) {
        ServiceResponse response = new ServiceResponse();
        integers.forEach(integer -> {
            reviewRepository.deleteById(integer);
        });
        response.setStatus("success");
        response.setData("Xóa thành công");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public List<ReviewProjection> getReviews(Integer proId) {
        return reviewRepository.findLimit(proId);
    }
}
