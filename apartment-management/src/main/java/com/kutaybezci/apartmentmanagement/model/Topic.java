package com.kutaybezci.apartmentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Topic {
    @Id
    @GeneratedValue
    private long topicId;
    @Column(unique = true, nullable = false)
    private String topicName;
    @Column
    private String description;
}
