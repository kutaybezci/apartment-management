package com.kutaybezci.apartmentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue
    private long personId;
    @Column(unique = true, nullable = false)
    private String username;
    @Column
    private String password;
    @Column
    private String fullname;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
}
