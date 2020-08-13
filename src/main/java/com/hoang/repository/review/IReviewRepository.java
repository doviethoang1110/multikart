package com.hoang.repository.review;

import com.hoang.entities.Review;
import com.hoang.projections.ReviewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReviewRepository extends JpaRepository<Review,Integer> {
    @Query(value = "select r.id as id,r.name as name,r.content as content,r.rating as rating,r.created_at as created from review r where r.status = true and r.product_id =:proId order by r.created_at desc",nativeQuery = true)
    List<ReviewProjection> findLimit(@Param("proId") Integer proId);
}
