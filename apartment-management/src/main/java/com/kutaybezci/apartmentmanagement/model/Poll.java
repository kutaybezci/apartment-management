package com.kutaybezci.apartmentmanagement.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Poll {
    @Id
    @GeneratedValue
    private long pollId;
    @Column(nullable = false, unique = true)
    private String pollName;
    @Column
    private String pollDescription;
    @Column(nullable = false)
    private Date validFrom;
    @Column(nullable = false)
    private Date validUntil;
    @Column
    private boolean anonymous;
    @Column
    private boolean swap;
}
