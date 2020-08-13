package com.hoang.repository.brand;

import com.hoang.dto.client.BrandDto;
import com.hoang.entities.Brand;
import com.hoang.projections.BrandProjections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBrandRepository extends JpaRepository<Brand,Integer> {
    @Query(value = "select b.id as id,b.name as name,b.slug as slug,b.image as image from brand b where b.status=1",nativeQuery = true)
    List<BrandProjections> findAllBrand();
    @Query("select b from Brand b order by b.createAt desc")
    Page<Brand> findAllOrderByCreate(Pageable pageable);
    @Query(value = "select b.slug from Brand b where b.slug like:slug")
    String findSlug(@Param("slug") String slug);
    @Query(value = "select b.slug from Brand b where b.id =:id")
    String findSlug(@Param("id") Integer id);
    Brand findBrandById(Integer id);
    @Query(value = "select p.id from Product p inner join Brand b on b.id = p.brand.id where b.id =:id")
    List<Integer> findProducts(@Param("id") Integer id);
    List<Brand> findByStatus(Short status);
}
