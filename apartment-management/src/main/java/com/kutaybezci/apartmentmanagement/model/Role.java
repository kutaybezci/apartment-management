package com.kutaybezci.apartmentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue
    private long roleId;
    @Column(unique = true, nullable = false)
    private String roleName;
}
