package com.hoang.repository.currency;

import com.hoang.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICurrencyRepository extends JpaRepository<Currency,Integer> {
    @Query(value = "select c.code from Currency c where c.code like:code")
    String uniqueCode(@Param("code") String code);
    @Query(value = "select c.code from Currency c where c.code =:id")
    String uniqueCode(@Param("id") Integer id);
}
