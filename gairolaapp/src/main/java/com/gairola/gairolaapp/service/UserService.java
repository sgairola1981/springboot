package com.gairola.gairolaapp.service;

import java.util.List;

import com.gairola.gairolaapp.dto.UserDto;
import com.gairola.gairolaapp.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
