package com.hoang.repository.sku;

import com.hoang.entities.Sku;
import com.hoang.projections.SkuProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISkuRepository extends JpaRepository<Sku,Integer> {
    @Query(value = "select s.id as id,s.code as code,s.importPrice as importPrice,s.exportPrice as exportPrice,s.stock as stock,GROUP_CONCAT(ov.name) as `option`,s.status as status,s.quantity as quantity " +
            "from sku_value sv inner join sku s on s.id = sv.sku_id " +
            "inner join option_value ov on ov.id = sv.option_value_id " +
            "where s.product_id =:id group by s.id,s.code order by s.updated_at desc", nativeQuery = true)
    List<SkuProjection> getAll(@Param("id") Integer id);
    @Query(value = "select s.code from Sku s where s.code like:code")
    String uniqueCode(@Param("code") String code);
    @Query(value = "select s from Sku s where s.id=:id")
    Sku findOne(@Param("id") Integer id);
    @Query(value = "select s from Sku s where s.code like:code")
    Sku findOne(@Param("code") String code);
}