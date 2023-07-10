package com.gairola.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

}
