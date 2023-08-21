package com.gairola.gairolaapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.gairola.gairolaapp.entity.UserInfo;

public interface UserInfoService {
    List<UserInfo> getUsers();

    UserInfo createUser(UserInfo userInfo);

    UserInfo UpdateUserForm(Integer userId);

    void DeleteUserForm(Optional<Integer> id);

    UserInfo getUserById(Integer userId);

    public Page<UserInfo> listAll(int pageNum, String sortField, String sortDir);

}
