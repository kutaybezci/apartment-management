package com.kutaybezci.apartmentmanagement.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RoleGroup {
    @NotBlank(message = "empty.roleGroupName")
    private String roleGroupName;
    private String description;
}
