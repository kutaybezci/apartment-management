package com.kutaybezci.apartmentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "role_group")
public class RoleGroupEntity {
    @Id
    @GeneratedValue
    private long roleGroupId;
    @Column(unique = true, nullable = false)
    private String roleGroupName;
    @Column
    private String description;
}
