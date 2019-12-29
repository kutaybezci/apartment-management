package com.kutaybezci.apartmentmanagement.dto;

import lombok.Data;

@Data
public class CreateUserRequest extends BasicRequest {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
}
