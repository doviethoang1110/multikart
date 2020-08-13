package com.hoang.repository.option;

import com.hoang.entities.Option;
import com.hoang.entities.Product;
import com.hoang.projections.OptionProjections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IOptionRepository extends JpaRepository<Option,Integer> {
//    @Query("select o.id as id,o.name as name from Option o")
//    List<OptionProjections> getOption();
    @Query(value = "select o.id as id,o.name as name from Option o where o.product.id =:id")
    List<OptionProjections> findByProduct(@Param("id") Integer id);
    @Query(value = "select o from Option o where o.id =:id")
    Option findOne(@Param("id") Integer id);
}
