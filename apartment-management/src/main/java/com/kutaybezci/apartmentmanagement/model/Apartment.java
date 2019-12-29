package com.kutaybezci.apartmentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Apartment {
    @Id
    @GeneratedValue
    private long apartmentId;
    @Column(nullable = false, unique = true)
    private String apartmentCode;
    @Column(nullable = false)
    private int share;
    @OneToOne
    @JoinColumn(name = "resident_user_id")
    private UserAccount resident;
    @OneToOne
    @JoinColumn(name = "owner_user_id")
    private UserAccount owner;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
