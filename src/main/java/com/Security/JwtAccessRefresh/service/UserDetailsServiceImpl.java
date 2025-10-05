package com.Security.JwtAccessRefresh.service;

import com.Security.JwtAccessRefresh.entity.UserInfo;
import com.Security.JwtAccessRefresh.model.UserInfoDto;
import com.Security.JwtAccessRefresh.repo.UserRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Data
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepo.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Could not found user . . .!!!");
        }
        return new CustomeUserDetails(user);
    }

    public UserInfo checkIfUserAlreadyExist(UserInfo userInfo){
        return userRepo.findByUsername(userInfo.getUsername());
    }

    public Boolean signUpUser(UserInfoDto userInfoDto){

        // Define a function to check userEmail, password is correct
        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        if (Objects.nonNull(checkIfUserAlreadyExist(userInfoDto))){
            return false;
        }
        String userId = UUID.randomUUID().toString();
        userRepo.save(new UserInfo(userId, userInfoDto.getUsername(),
                userInfoDto.getPassword(), new HashSet<>()));

        return true;
    }
}
