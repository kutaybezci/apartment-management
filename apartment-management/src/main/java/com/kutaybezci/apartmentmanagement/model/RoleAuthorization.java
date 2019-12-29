package com.kutaybezci.apartmentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class RoleAuthorization {
    @Id
    @GeneratedValue
    private long roleAuthorizationId;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(nullable = false)
    private String authorizationCode;
}
