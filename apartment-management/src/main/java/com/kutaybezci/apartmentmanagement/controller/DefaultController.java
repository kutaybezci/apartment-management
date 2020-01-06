package com.kutaybezci.apartmentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping(path = { "/", "/login" })
    public String login() {
	return "login";
    }

    @GetMapping("/menu")
    public String menu() {
	return "menu";
    }

}
