package com.kutaybezci.apartmentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name = "apartment")
public class ApartmentEntity {
    @Id
    @GeneratedValue
    private long apartmentId;
    @Column(nullable = false, unique = true)
    private String apartmentCode;
    @Column(nullable = false)
    private int share;
    @OneToOne
    @JoinColumn(name = "resident_person_id")
    private PersonEntity resident;
    @OneToOne
    @JoinColumn(name = "owner_person_id")
    private PersonEntity owner;
    @OneToOne
    @JoinColumn(name = "account_id")
    private AccountingEntity account;

}
