package com.Security.JwtAccessRefresh.repo;

import com.Security.JwtAccessRefresh.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshtokenRepo extends CrudRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);
}
