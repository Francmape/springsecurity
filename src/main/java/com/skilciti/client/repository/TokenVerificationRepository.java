package com.skilciti.client.repository;

import com.skilciti.client.entity.TokenVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenVerificationRepository extends JpaRepository<TokenVerification, Long> {
}
