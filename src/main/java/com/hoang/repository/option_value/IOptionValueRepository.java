package com.hoang.repository.option_value;

import com.hoang.entities.OptionValue;
import com.hoang.projections.OptionValueProjections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface IOptionValueRepository extends JpaRepository<OptionValue,Integer> {
//    @Query(value = "select distinct new com.hoang.dto.client.OptionValueDto(p.id,p.name) " +
//            "from OptionValue p inner join SkuValue s on s.optionValue.id=p.id " +
//            "where s.productOption.product.slug like:slug and p.option.id=:id")
//    List<OptionValueDto> getAllOptionValue(@Param("slug") String slug,@Param("id") int id);
    @Query(value = "select distinct o.id as id,o.name as name " +
            "from OptionValue o where o.option.name like:name group by o.name")
    Set<OptionValueProjections> finAllDto(@Param("name") String name);
//    @Query(value = "select ov.id as id,ov.name as label,op.id as option from OptionValue ov inner join Option op on ov.option.id = op.id where ov.status = 0")
//    List<OptionValueProjections> findOptionForClient();
//    @Query(value = "from OptionValue o where")
//    OptionValue findOne(@Param("id") Integer id);
}