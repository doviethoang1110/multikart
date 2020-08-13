package com.hoang.repository.roles;


import com.hoang.entities.Permissions;
import com.hoang.entities.Roles;
import com.hoang.projections.RoleProjections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRolesRepository extends JpaRepository<Roles,Integer> {
    @Query(value = "select r.id as id,r.name as name ,r.display_name as displayName," +
            "GROUP_CONCAT(p.display_name) as permissions" +
            " from roles r inner join permission_role pr on pr.role_id = r.id " +
            "inner join permissions p on pr.permission_id = p.id GROUP BY r.id,r.name,r.display_name",nativeQuery = true)
    List<RoleProjections> findRoles();
    @Query(value ="select r.name from Roles r where r.name like:name")
    String findName(@Param("name") String name);
    @Query(value ="select r.displayName from Roles r where r.displayName like:displayName")
    String findDisplayName(@Param("displayName") String displayName);
    @Query(value ="select r.name from Roles r where r.id =:id")
    String findName(@Param("id") Integer id);
    @Query(value ="select r.displayName from Roles r where r.id =:id")
    String findDisplayName(@Param("id") Integer id);
    @Query(value = "from Roles r join fetch r.permissions p where r.id =:id")
    Roles findOne(@Param("id") Integer id);
}

