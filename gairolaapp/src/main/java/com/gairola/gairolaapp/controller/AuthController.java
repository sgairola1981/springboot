package com.gairola.gairolaapp.controller;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.gairola.gairolaapp.dto.UserDto;
import com.gairola.gairolaapp.entity.Book;
import com.gairola.gairolaapp.entity.User;
import com.gairola.gairolaapp.service.BookService;
import com.gairola.gairolaapp.service.UserService;
import com.gairola.gairolaapp.util.RestClientUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/main")
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    private RestClientUtil client;

    private BookService bookService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @Operation(summary = "This is to login form .")
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @Operation(summary = "This is to call service")
    @GetMapping("/service")
    public String ServiceForm() throws URISyntaxException {

        return "service";
    }

    @Operation(summary = "This is to use to home page")
    @GetMapping("/home")
    public String ServiceHome() {
        // ResponseEntity<Book> responseEntity = restTemplate
        // .getForEntity("http://localhost:9000/book/501", Book.class);

        Book[] books = restTemplate.getForObject("http://localhost:9000/book/all",
                Book[].class);

        System.out.println("*************************************");

        for (Book e : books) {
            System.out.println(e);
        }
        String url = "http://localhost:9000/book/{bookId}";
        Integer bookId = 501;
        Book book = restTemplate.getForObject(url, Book.class, bookId);
        System.out.println(book.toString());

        // return "home";
        return "formdata";

    }

    // handler method to handle user registration request
    @Operation(summary = "This is to Regester a new user in the database")
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @Operation(summary = "This is save user information in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Details of All the Participants", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
            BindingResult result,
            Model model) {
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/main/register?success";
    }

    @Operation(summary = "This is to Details of All the user from database")
    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";

    }
}
