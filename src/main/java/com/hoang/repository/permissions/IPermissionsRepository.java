package com.hoang.repository.permissions;

import com.hoang.dto.server.PermissionDto;
import com.hoang.entities.Permissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPermissionsRepository extends JpaRepository<Permissions,Integer> {
    @Query(value = "select p.name from Permissions p where p.name like:name")
    String findOne(@Param("name") String name);
    @Query(value = "select p.name from Permissions p where p.id =:id")
    String findOne(@Param("id") Integer id);
    Page<Permissions> findAllByOrderByCreatedAtDesc(Pageable pageable);
    @Query(value = "from Permissions where id =:id")
    Permissions findOneById(@Param("id") Integer id);
    @Query(value = "select p from Permissions p left join fetch p.roles r where p.id =:id")
    Permissions insert(@Param("id") Integer id);
    @Query(value = "select new com.hoang.dto.server.PermissionDto(p.id,p.displayName) from Permissions p")
    List<PermissionDto> findDto();
}
