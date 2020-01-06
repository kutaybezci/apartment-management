package com.kutaybezci.apartmentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "accounting")
public class AccountingEntity {
    @Id
    @GeneratedValue
    private long accountId;
    @Column(unique = true, nullable = false)
    private String accountName;
}
