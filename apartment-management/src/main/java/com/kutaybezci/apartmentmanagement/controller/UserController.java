package com.kutaybezci.apartmentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kutaybezci.apartmentmanagement.bl.UserBO;
import com.kutaybezci.apartmentmanagement.dto.BasicRequest;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserBO userBO;

    @GetMapping("/login/{username}/{password}")
    private boolean login(@PathVariable String username, @PathVariable String password) {
	BasicRequest request = new BasicRequest();
	request.setLoginName(username);
	request.setLoginPassword(password);
	return userBO.doLogin(request);
    }
}
