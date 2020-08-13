package com.hoang.repository.payment;

import com.hoang.entities.PaymentStatus;
import com.hoang.projections.PaymentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPaymentStatusRepository extends JpaRepository<PaymentStatus,Integer> {
    @Query(value = "select p.id as id,p.name as name,p.status as status from PaymentStatus p")
    List<PaymentProjection> getAll();
    @Query(value = "select p.id as id,p.name as name,p.status as status from PaymentStatus p where p.id =:id")
    PaymentProjection findOne(@Param("id") Integer id);
    @Query(value = "select p.name from PaymentStatus p where p.name like:name")
    String uniqueName(@Param("name") String name);
    @Query(value = "select p.name from PaymentStatus p where p.id =:id")
    String uniqueName(@Param("id") Integer id);
}
