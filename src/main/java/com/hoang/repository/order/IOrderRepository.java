package com.hoang.repository.order;

import com.hoang.entities.Order;
import com.hoang.projections.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order,Integer> {
    @Query(value = "select o.id as id,c.email as email,o.total as total,o.currency as currency,o.rate as rate,o.paymentMethod as paymentMethod,o.createdAt as created,os.name as orderStatus" +
            " from Order o inner join OrderStatus os on os.id = o.orderStatus.id inner join Customer c on c.id = o.customer.id order by o.updatedAt desc")
    Page<OrderProjection> getAll(Pageable pageable);
    @Query(value = "from Order o " +
            "join fetch o.customer c " +
            "join fetch o.deliveryStatus ds " +
            "join fetch o.orderStatus os " +
            "join fetch o.paymentStatus pa " +
            "join fetch o.orderDetails od " +
            "join fetch od.product p " +
            "where o.id =:id")
    Order findOne(@Param("id") Integer id);
    @Query(value = "select o.id as id,o.name as name from OrderStatus o where o.status = 0")
    List<OrderStatusProjection> findOrderStatus();
    @Query(value = "select o.id as id,os.name as orderStatus,de.name as shippingStatus,o.total as total,o.currency as currency from Order o " +
            "inner join OrderStatus os on os.id = o.orderStatus.id " +
            "inner join DeliveryStatus de on de.id = o.deliveryStatus.id " +
            "where o.customer.id =:id")
    List<OrderClientProjection> findOrders(@Param("id") Integer id);
    @Query(value = "select distinct concat(p.name,' (',group_concat(ov.name),')') as name,od.quantity as quantity,od.price as price from " +
            "orderDetail od " +
            "inner join orders o on o.id = od.order_id " +
            "inner join product p on od.product_id = p.id " +
            "inner join sku s on s.code like od.sku " +
            "inner join sku_value sv on sv.sku_id = s.id " +
            "inner join option_value ov on ov.id = sv.option_value_id " +
            "where od.order_id =:id group by s.id",nativeQuery = true)
    List<OrderDetailClient> findOrderDetails(@Param("id") Integer id);
}
