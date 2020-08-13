package com.hoang.repository.category;

import com.hoang.dto.client.CategoryDto;
import com.hoang.entities.Category;
import com.hoang.projections.ProductDtoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ICategoryRepository extends JpaRepository<Category,Integer> {
    @Query(value = "select new com.hoang.dto.client.CategoryDto(c.id,c.name,c.slug,c.parentId) from Category c where c.status = 0")
    List<CategoryDto> findAllDto();

    @Query(value = "select new com.hoang.dto.client.CategoryDto(c.id,c.name,c.slug,c.parentId) from Category c where c.parentId =:id")
    List<CategoryDto> findByParentId(@Param("id") Integer id);

    @Query(value = "select c.slug from Category c where c.slug like:slug")
    String findSlug(@Param("slug") String slug);
    @Query(value = "select c.slug from Category c where c.id =:id")
    String findSlug(@Param("id") Integer id);

    @Query(value = "select p.id from Product p inner join Category c on c.id = p.category.id where c.id =:id")
    List<Integer> findProduct(@Param("id") Integer id);
    @Query(value = "select c.id from Category c where c.parentId =:id")
    List<Integer> findCategories(@Param("id") Integer id);

    @Query(value = "with recursive cat_tree as " +
            "(select id from category where slug like:slug union all select child.id from category as child join cat_tree as parent on parent.id = child.parent_id) " +
            "select p.id as id,p.name as name,p.slug as slug,p.discount as discount,p.priority as priority,p.image as image,ca.name as categoryName,ca.slug as categorySlug,min(s.exportPrice) as priceFrom,max(s.exportPrice) as priceTo from product p inner join category ca on ca.id = p.category_id inner join sku s on s.product_id = p.id  where p.category_id in (select * from cat_tree) and p.status = 0 group by p.name",nativeQuery = true)
    List<ProductDtoProjection> findProductByCategory(@Param("slug") String slug);
}
