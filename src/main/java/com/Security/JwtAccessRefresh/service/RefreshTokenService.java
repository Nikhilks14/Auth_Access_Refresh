package com.Security.JwtAccessRefresh.service;

import com.Security.JwtAccessRefresh.entity.RefreshToken;
import com.Security.JwtAccessRefresh.entity.UserInfo;
import com.Security.JwtAccessRefresh.repo.RefreshtokenRepo;
import com.Security.JwtAccessRefresh.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshtokenRepo refreshtokenRepo;
    private final UserRepo userRepo;

    public RefreshToken createRefreshToken(String username) {
        UserInfo user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();

        return refreshtokenRepo.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if (token.getExpiryDate().compareTo(Instant.now())<0){
            refreshtokenRepo.delete(token);
            throw new RuntimeException(token.getToken() + "Refresh token is expired. Please make a new login");
        }
        return token;
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshtokenRepo.findByToken(token);
    }

}
