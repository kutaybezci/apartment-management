package com.kutaybezci.apartmentmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "person_role_group")
public class PersonRoleGroupEntity {
    @Id
    @GeneratedValue
    private long personRoleGroupId;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
    @ManyToOne
    @JoinColumn(name = "role_group_id")
    private RoleGroupEntity roleGroup;

}
