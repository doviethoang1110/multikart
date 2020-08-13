package com.hoang.repository.coupon;

import com.hoang.dto.server.CustomerDto;
import com.hoang.entities.Coupon;
import com.hoang.projections.CouponDtoProjection;
import com.hoang.projections.CouponProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICouponRepository extends JpaRepository<Coupon,Integer> {
    @Query(value = "select c.id as id,c.title as title,c.code as code,c.detail as detail,c.startDate as startDate,c.endDate as endDate,c.type as type,c.status as status " +
            "from Coupon c")
    List<CouponProjection> getAll();
    @Query(value = "select new com.hoang.dto.server.CustomerDto(c.id,c.name,c.phone,c.email) " +
            "from Customer c")
    List<CustomerDto> getUsers();
    @Query(value = "from Coupon c left join fetch c.customers cu where c.id =:id")
    Coupon findOne(@Param("id") Integer id);
    @Query(value = "from Coupon c left join fetch c.customers cu where c.code like:code")
    Coupon findOne(@Param("code") String code);
    @Query(value = "select c.id as id,c.title as title,c.code as code,c.detail as detail,c.startDate as startDate,c.endDate as endDate,c.type as type,c.status as status from Coupon c where c.id =:id")
    CouponProjection find(@Param("id") Integer id);
    @Query(value = "select c.code as code,c.type as type,c.detail as detail from coupon c inner join customer_coupon cc on cc.coupon_id = c.id " +
            "inner join customer cu on cu.id = cc.customer_id where c.code like:code and cu.id =:id and c.status = 0",nativeQuery = true)
    CouponDtoProjection getCoupon(@Param("code") String code, @Param("id") Integer id);
    @Query(value = "select c.code as code,c.type as type,c.detail as detail from coupon c inner join customer_coupon cc on cc.coupon_id = c.id " +
            "inner join customer cu on cu.id = cc.customer_id where cu.id =:id and c.status = 0",nativeQuery = true)
    List<CouponDtoProjection> getCoupons(@Param("id") Integer id);
    @Query(value = "select c.code from Coupon c where c.code like:code")
    String findCode(@Param("code") String code);
    @Query(value = "select c.code from Coupon c where c.id =:id")
    String findCode(@Param("id") Integer id);
}
