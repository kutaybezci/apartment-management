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
@Entity(name = "topic_message")
public class TopicMessageEntity {
    @Id
    @GeneratedValue
    private long messageId;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private Timestamp entryDate;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;
}
