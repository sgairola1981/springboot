package com.gairola.gairolaapp.service;

import java.util.List;

import com.gairola.gairolaapp.dto.UserDto;
import com.gairola.gairolaapp.entity.Book;
import com.gairola.gairolaapp.entity.User;
import com.gairola.gairolaapp.entity.UserInfo;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();

}
