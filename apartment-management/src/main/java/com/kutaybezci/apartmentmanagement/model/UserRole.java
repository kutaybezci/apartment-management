package com.kutaybezci.apartmentmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class UserRole {
    @Id
    @GeneratedValue
    private long userRoleId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

}
