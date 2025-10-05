package com.Security.JwtAccessRefresh.repo;

import com.Security.JwtAccessRefresh.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
}
