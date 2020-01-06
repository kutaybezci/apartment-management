package com.kutaybezci.apartmentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "poll_choice")
public class PollChoiceEntity {
    @Id
    @GeneratedValue
    private long pollChoiceId;
    @Column(nullable = false)
    private String choiceName;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn
    private PollEntity poll;
    @Column
    private int totalVoted;
}
