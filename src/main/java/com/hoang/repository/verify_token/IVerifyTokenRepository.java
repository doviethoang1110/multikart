package com.hoang.repository.verify_token;

import com.hoang.entities.Customer;
import com.hoang.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface IVerifyTokenRepository extends JpaRepository<VerificationToken,Integer> {
    @Query(value = "from VerificationToken v where v.token like:token")
    VerificationToken findByToken(@Param("token") String token);
}
