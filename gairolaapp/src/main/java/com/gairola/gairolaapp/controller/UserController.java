package com.gairola.gairolaapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gairola.gairolaapp.entity.UserInfo;
import com.gairola.gairolaapp.service.UserInfoService;

@Controller
@RequestMapping("/data")
public class UserController {

    private final UserInfoService userService;

    public UserController(UserInfoService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        List<UserInfo> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("userInfo", new UserInfo());
        return "userform";
    }

    @RequestMapping(value = "/usersdatasave", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute UserInfo userInfo) {
        UserInfo user = userService.createUser(userInfo);
        List<UserInfo> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("userInfo", new UserInfo());
        return "userform";
    }

    @GetMapping(path = { "/updateForm", "/updateForm/{id}" })
    public String editUserById(Model model,
            @PathVariable("id") Optional<Integer> id) {

        if (id.isPresent()) {
            UserInfo entity = userService.getUserById(id.get());
            model.addAttribute("userInfo", entity);
        } else {
            model.addAttribute("userInfo", new UserInfo());
        }

        return "userform";
    }

    @GetMapping("/deleteForm/{id}")
    public String deleteUserById(Model model,
            @PathVariable("id") Optional<Integer> id) {
        userService.DeleteUserForm(id);
        List<UserInfo> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("userInfo", new UserInfo());
        return "userform";

    }

}