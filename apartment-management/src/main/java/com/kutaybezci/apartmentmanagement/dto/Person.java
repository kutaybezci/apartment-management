package com.kutaybezci.apartmentmanagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Person {
    @NotBlank(message = "empty.username")
    @Pattern(regexp = "[A-Z_]+", message = "invalid.username")
    private String username;
    private String password;
    private String fullname;
    @Pattern(regexp = "^$|^([a-z0-9_\\.]+)@([a-z0-9_\\]+)$", message = "invalid.mail")
    private String email;
    @Pattern(regexp = "[0-9]*", message = "invalid.phone")
    private String phone;
}
