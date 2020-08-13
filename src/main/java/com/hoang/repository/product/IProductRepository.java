package com.hoang.repository.product;

import com.hoang.dto.client.*;
import com.hoang.entities.Option;
import com.hoang.entities.Product;
import com.hoang.projections.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface IProductRepository extends JpaRepository<Product,Integer> {
    static final String query = "select new com.hoang.dto.client.ProductDto(p.id,p.name,p.slug,c.name,c.slug,p.discount,p.priority,p.image,5,min(s.exportPrice),max(s.exportPrice)) " +
            "from Product p inner join Brand b on b.id = p.brand.id inner join Category c on c.id = p.category.id inner join Sku s on s.product.id = p.id where p.status = 0";

    @Query(value = "select p.id as id,p.name as name,c.name as category,b.name as brand,p.slug as slug," +
            " p.priority as priority,p.image as image,p.status as status,p.vision as vision" +
            " from Product p inner join Brand b on b.id = p.brand.id inner join Category c on c.id = p.category.id order by p.createAt desc")
    Page<ProductProjection> getByServer(Pageable pageable);
    @Query(value = "select new com.hoang.dto.server.ProductDto(p.id,p.name,c.id,b.id,p.slug,p.discount,p.priority,p.image,p.description,p.imageList,p.status,p.vision)" +
            " from Product p inner join Brand b on b.id = p.brand.id inner join Category c on c.id = p.category.id where p.id =:id")
    com.hoang.dto.server.ProductDto getOneForServer(@Param("id") Integer id);
    @Query(value = "select p.name from Product p where p.name like:name")
    String uniqueName(@Param("name") String name);
    @Query(value = "select p.slug from Product p where p.slug like:slug")
    String uniqueSlug(@Param("slug") String slug);
    @Query(value = "select p.name from Product p where p.id =:id")
    String uniqueName(@Param("id") Integer id);
    @Query(value = "select p.slug from Product p where p.id =:id")
    String uniqueSlug(@Param("id") Integer id);
    @Query(value = "select p from Product p join fetch p.category c join fetch p.brand b where p.id =:id")
    Product getById(@Param("id") Integer id);
    @Query(value = "select p.id as id,p.name as name,p.slug as slug,p.discount as discount,p.priority as priority,p.image as image,ca.name as categoryName,ca.slug as categorySlug,min(s.exportPrice) as priceFrom,max(s.exportPrice) as priceTo from Product p inner join Category ca on ca.id = p.category.id inner join Sku s on s.product.id = p.id where p.category.slug like:slug and p.status = 0")
    List<ProductDtoProjection> findProductByCategory(@Param("slug") String slug);
    @Query(value = query+" group by p.name")
    Page<ProductDto> filter(Pageable pageable);
    @Query(value = query+" and s.exportPrice between :from and :to group by p.name")
    Page<ProductDto> filter(Pageable pageable,@Param("from") Double from,@Param("to") Double to);
    @Query(value = query+" and b.slug like:slug group by p.name")
    Page<ProductDto> filter(Pageable pageable,@Param("slug") String slug);
    @Query(value = query+" and b.slug like:slug and s.exportPrice between :from and :to group by p.name")
    Page<ProductDto> filter(Pageable pageable,@Param("slug") String slug,@Param("from") Double from,@Param("to") Double to);


    @Query(value = "select new com.hoang.dto.client.OptionValueDto(ov.id,ov.name,ov.option.id) " +
            "from OptionValue ov where ov.option.product.slug like:name order by ov.createAt asc")
    Set<OptionValueDto> findOptionValues(@Param("name") String slug);
    @Query(value = "select new com.hoang.dto.client.OptionDto(o.id,o.name) from Option o where o.product.slug like:slug order by o.createAt asc")
    Set<OptionDto> findOptions(@Param("slug") String slug);
    @Query(value = "select p from Product p where p.slug like:slug")
    Product findBySlug(@Param("slug") String slug);
    @Query(value = "select s.code as code,s.stock as stock,s.exportPrice as price " +
            "from sku_value sv inner join sku s on s.id = sv. sku_id " +
            "where sv.option_value_id in :ids group by s.code having count(s.code) =:number",nativeQuery = true)
    SkuValueProjection findSku(@Param("ids") List<Integer> integers,@Param("number") Integer number);
    @Query(value = query+" group by p.name")
    List<ProductDto> findTopEight();
    @Query(value = "select c.slug as categorySlug,p.slug as slug,p.image as image,p.name as name,min(s.exportPrice) as priceFrom,max(s.exportPrice) as priceTo " +
            "from Product p inner join Category c on c.id = p.category.id inner join Sku s on s.product.id = p.id where p.status = 0 and p.name like %:keyword% group by p.name")
    Page<ProductSearchProjection> search(@Param("keyword") String keyword, Pageable pageable);

}