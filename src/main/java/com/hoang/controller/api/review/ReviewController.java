package com.hoang.controller.api.review;

import com.hoang.entities.Review;
import com.hoang.service.ReviewService;
import com.hoang.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RequestMapping(value = "/api/v1")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PreAuthorize("hasAnyAuthority('READ_REVIEW')")
    @GetMapping(value = "reviews")
    public ResponseEntity<Map<String,Object>> getAll(
            @RequestParam(value = "page",defaultValue = "0",required = false) Integer page
    ){
        return reviewService.findAll(page);
    }
    @PreAuthorize("hasAnyAuthority('UPDATE_REVIEW')")
    @PutMapping(value = "reviews")
    public ResponseEntity<ServiceResponse> update(@RequestBody Map<String,Object> map){
        return reviewService.update(map);
    }
    @PreAuthorize("hasAnyAuthority('DELETE_REVIEW')")
    @DeleteMapping(value = "reviews/{id}")
    public ResponseEntity<ServiceResponse> delete(@PathVariable Integer id){
        return reviewService.delete(id);
    }
    @PreAuthorize("hasAnyAuthority('DELETE_REVIEW')")
    @PostMapping(value = "reviews/multidelete")
    public ResponseEntity<ServiceResponse> multidelete(@RequestBody List<Integer> integers){
        return reviewService.multidelete(integers);
    }
}
