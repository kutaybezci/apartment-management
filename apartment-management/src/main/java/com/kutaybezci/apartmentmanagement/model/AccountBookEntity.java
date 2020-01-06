package com.kutaybezci.apartmentmanagement.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "account_book")
public class AccountBookEntity {
    @Id
    @GeneratedValue
    private long bookId;
    @Column(unique = true, nullable = false)
    private String bookName;
    @Column
    private Date validFrom;
    @Column
    private Date validUntil;
    @Column
    private boolean closed = true;

}
