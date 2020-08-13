package com.hoang.repository.wishlist;

import com.hoang.entities.WishList;
import com.hoang.projections.WishListProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IWishListRepository extends JpaRepository<WishList,Integer> {
    @Query(value = "select w.id as id,p.name as name,c.name as categoryName,p.image as image,min(s.exportPrice) as priceFrom,max(s.exportPrice) as priceTo " +
            "from WishList w inner join Product p on p.id = w.product.id " +
            "inner join Category c on c.id = p.category.id " +
            "inner join Sku s on s.product.id = p.id " +
            "where w.customer.id =:id group by p.name")
    List<WishListProjection> findWishList(@Param("id") Integer id);
    @Query(value = "select w.id from WishList w where w.product.id =:product_id and w.customer.id =:customer_id")
    Integer checkExist(@Param("product_id") Integer productId,@Param("customer_id") Integer customerId);
}
