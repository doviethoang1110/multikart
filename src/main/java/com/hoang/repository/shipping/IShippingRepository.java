package com.hoang.repository.shipping;

import com.hoang.entities.DeliveryStatus;
import com.hoang.projections.ShippingProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IShippingRepository extends JpaRepository<DeliveryStatus,Integer> {
    @Query(value = "select d.id as id,d.name as name,d.status as status from DeliveryStatus d")
    List<ShippingProjection> getAll();
    @Query(value = "select d.id as id,d.name as name,d.status as status from DeliveryStatus d where d.id =:id")
    ShippingProjection findOne(@Param("id") Integer id);
    @Query(value = "select d.name from DeliveryStatus d where d.name like:name")
    String uniqueName(@Param("name") String name);
    @Query(value = "select d.name from DeliveryStatus d where d.id =:id")
    String uniqueName(@Param("id") Integer id);
}
