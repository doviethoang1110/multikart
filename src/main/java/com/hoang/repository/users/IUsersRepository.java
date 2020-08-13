package com.hoang.repository.users;

import com.hoang.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IUsersRepository extends JpaRepository<Users,Integer> {
    @Query(value = "select u from Users u join fetch u.roles r join fetch r.permissions where u.name like:name")
    Optional<Users> findByName(@Param("name") String name);
    @Query(value = "select u.name from Users u where u.email like:email")
    String findByEmail(@Param("email") String email);
    @Query(value = "from Users u where u.name like:name")
    Users findName(@Param("name") String username);
}
