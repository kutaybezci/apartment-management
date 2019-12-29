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
public class PollVoting {
    @Id
    @GeneratedValue
    private long pollVotingId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;
    @ManyToOne
    @JoinColumn(name = "choice_id")
    private PollChoice pollChoice;
    @Column(nullable = false)
    private Timestamp entryDate;

}
