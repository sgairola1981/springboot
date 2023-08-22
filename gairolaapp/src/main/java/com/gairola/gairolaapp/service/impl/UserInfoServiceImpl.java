package com.gairola.gairolaapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gairola.gairolaapp.entity.UserInfo;
import com.gairola.gairolaapp.repository.UserInfoRepository;
import com.gairola.gairolaapp.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {

        this.userInfoRepository = userInfoRepository;
    }

    public List<UserInfo> getUsers() {
        return userInfoRepository.findAll();
    }

    public UserInfo createUser(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo UpdateUserForm(Integer userId) {
        return userInfoRepository.findById(userId).get();
    }

    @Override
    public void DeleteUserForm(Optional<Integer> userId) {
        userInfoRepository.deleteById(userId.get());
    }

    @Override
    public UserInfo getUserById(Integer userId) {
        return userInfoRepository.findById(userId).get();

    }

    @Override
    public Page<UserInfo> listAll(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return userInfoRepository.findAll(pageable);
    }

}
