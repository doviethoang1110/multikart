package com.hoang.repository.customer;

import com.hoang.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "select c.name " +
            "from Customer c where c.enabled = true and c.name like:param")
    String checkName(@Param("param") String param);
    @Query(value = "select c.email " +
            "from Customer c where c.enabled = true and c.email like:param")
    String checkEmail(@Param("param") String param);
    @Query(value = "select c.phone " +
            "from Customer c where c.enabled = true and c.phone like:param")
    String checkPhone(@Param("param") String param);
    @Query(value = "select c from Customer c where c.email like:email and c.enabled = true")
    Optional<Customer> findByEmail(@Param("email") String email);
    @Query(value = "from Customer c left join fetch c.coupons co where c.id =:id")
    Customer findOne(@Param("id") Integer id);
    @Query(value = "select count(c.customer_id) from customer_coupon c where c.customer_id =:ids and c.coupon_id =:id",nativeQuery = true)
    Integer findCustomers(@Param("ids") Integer ids,@Param("id") Integer id);
}
