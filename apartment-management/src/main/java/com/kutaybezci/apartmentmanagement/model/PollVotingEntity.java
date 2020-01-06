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
@Entity(name = "poll_voting")
public class PollVotingEntity {
    @Id
    @GeneratedValue
    private long pollVotingId;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
    @ManyToOne
    @JoinColumn(name = "choice_id")
    private PollChoiceEntity pollChoice;
    @Column(nullable = false)
    private Timestamp entryDate;

}
