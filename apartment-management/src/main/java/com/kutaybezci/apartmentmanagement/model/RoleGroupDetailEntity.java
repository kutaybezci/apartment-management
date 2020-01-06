package com.kutaybezci.apartmentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "role_group_detail")
public class RoleGroupDetailEntity {
    @Id
    @GeneratedValue
    private long roleGroupDetailId;
    @ManyToOne
    @JoinColumn(name = "role_group_id")
    private RoleGroupEntity roleGroup;
    @Column(nullable = false)
    private String roleCode;
}
