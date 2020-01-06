package com.kutaybezci.apartmentmanagement.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "account_book_entry")
public class AccountBookEntryEntity {
    @Id
    @GeneratedValue
    private long bookEntryId;
    @Column(nullable = false)
    private Timestamp entryDate;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private Date deadline;
    @ManyToOne
    @JoinColumn(name = "entry_person_id")
    private PersonEntity entryPerson;
}
