package com.hoang.repository.order_status;

import com.hoang.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IOrderStatusRepository extends JpaRepository<OrderStatus,Integer> {
    @Query(value = "select o.name from OrderStatus o where o.name like:name")
    String uniqueName(@Param("name") String name);
    @Query(value = "select o.name from OrderStatus o where o.id =:id")
    String uniqueName(@Param("id") Integer id);
    @Query(value = "from OrderStatus o where o.id =:id")
    OrderStatus findOne(@Param("id") Integer id);
}
