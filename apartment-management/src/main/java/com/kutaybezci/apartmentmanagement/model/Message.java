package com.kutaybezci.apartmentmanagement.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue
    private long messageId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private Timestamp entryDate;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
}
