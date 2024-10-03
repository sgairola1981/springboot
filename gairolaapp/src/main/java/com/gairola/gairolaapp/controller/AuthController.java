package com.gairola.gairolaapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.gairola.gairolaapp.config.ExternalAPICaller;
import com.gairola.gairolaapp.dto.UserDto;
import com.gairola.gairolaapp.entity.Book;
import com.gairola.gairolaapp.entity.User;
import com.gairola.gairolaapp.service.BookService;
import com.gairola.gairolaapp.service.UserService;
import com.gairola.gairolaapp.util.RestClientUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private ExternalAPICaller externalAPICaller;

    private Book forObject;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home() {
        return "login";
    }

    @Operation(summary = "This is to login form .")
    @GetMapping(value = { "/", "/login" })
    public String loginForm(HttpServletRequest request) {
        System.out.println("888888888888888888888888  loginForm");
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        System.out.println("888888888888888888888888  loginForm" + username);
        session.setAttribute("username", username);
        return "login";
    }

    @Operation(summary = "This is to call service")
    @GetMapping("/service")
    public String ServiceForm() throws URISyntaxException {

        return "service";
    }

    @Operation(summary = "This is to call service")
    @GetMapping("/contactus")
    @CircuitBreaker(name = "bookservice", fallbackMethod = "getProductFallback")
    public String ContactUsForm(Model model) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String bookId = "501";
        // Book book = bookService.findBooks(bookId);

        String url = "http://localhost:9000/book/{bookId}";

        Book book = restTemplate.getForObject(url, Book.class, bookId);
        System.out.println(book.toString());
        // ****************
        // getUsername() - Returns the username used to authenticate the user.
        System.out.println("User name: " + userDetails.getUsername());
        // getAuthorities() - Returns the authorities granted to the user.
        System.out.println("User has authorities: " + userDetails.getAuthorities());
        // book = callBookService();
        System.out.println(book.toString());
        model.addAttribute("Book", book);
        model.addAttribute("user", userDetails.getUsername());

        return "contactus";
    }

    public String getProductFallback(Model model, Exception exception) {
        logger.info("Rate limit has applied, So no further calls are getting accepted");
        System.out.println("getProductFallback===>  " + exception.getMessage());
        Book book = new Book();
        book.setBookId(100);
        book.setBookName("Testing");
        book.setBookCost(200.33);
        // return new ResponseEntity<>(book, HttpStatus.OK);
        return "contactus";

    }

    public ResponseEntity<String> getAPIFallBack(Exception e) {
        return new ResponseEntity<String>("subscribe service is down", HttpStatus.OK);
    }

    @Operation(summary = "This is to call service")
    @GetMapping("/welcome")
    public String WelcomeForm(HttpSession session, Model model) throws URISyntaxException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // getUsername() - Returns the username used to authenticate the user.
        System.out.println("User name: " + userDetails.getUsername());

        // getAuthorities() - Returns the authorities granted to the user.
        System.out.println("User has authorities: " + userDetails.getAuthorities());
        model.addAttribute("user", userDetails.getUsername());
        return "welcome";
    }

    @Operation(summary = "This is to call service")
    @GetMapping("/customer")
    public String CustomerData() throws URISyntaxException {

        return "customer";
    }

    @Operation(summary = "This is to use to home page")
    @GetMapping("/home")
    public String ServiceHome() {

        System.out.println("KKKKKKKKKKKKKKKKKKKK ServiceHome");

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

    // handler method to handle user registration request
    @Operation(summary = "This is to Regester a new user in the database")
    @GetMapping("/user")
    public String userForm(Model model) {
        model.addAttribute("currentPage", 1);
        System.out.println("CCCCCCCCCCCCCCCCCC userForm");
        return "userform";
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
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA users  List");
        model.addAttribute("users", users);
        model.addAttribute("currentPage", 1);
        return "users";

    }

    @GetMapping("/getInvoice")
    public Book getInvoice() {
        logger.info("getInvoice() call starts here");
        String bookId = "501";
        Book b = bookService.findBooks(bookId);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA users  List" + b.getBookName());
        /*
         * String url = "http://localhost:9000/book/{bookId}";
         * forObject = restTemplate.getForObject(url, Book.class, bookId);
         * logger.info("Response :" + forObject.getBookName());
         * System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA users  List" +
         * forObject.getBookName());
         * return forObject;
         */
        return b;
    }

    /*
     * public Book getInvoiceFallback(Exception e) {
     * 
     * logger.info("---RESPONSE FROM FALLBACK METHOD---");
     * System.out.println("-RESPONSE FROM FALLBACK METHOD---");
     * Book book = new Book();
     * book.setBookId(100);
     * book.setBookName("Testing");
     * book.setBookCost(200.33);
     * 
     * return book;
     * }
     */
}
