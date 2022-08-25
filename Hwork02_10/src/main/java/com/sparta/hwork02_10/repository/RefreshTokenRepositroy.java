package com.sparta.hwork02_10.repository;

import com.sparta.hwork02_10.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepositroy extends JpaRepository<RefreshToken, String> {
}
