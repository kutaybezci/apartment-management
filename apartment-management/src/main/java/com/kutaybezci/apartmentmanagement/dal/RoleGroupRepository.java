package com.kutaybezci.apartmentmanagement.dal;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kutaybezci.apartmentmanagement.model.RoleGroupEntity;

public interface RoleGroupRepository extends CrudRepository<RoleGroupEntity, Long> {
    Optional<RoleGroupEntity> findByRoleGroupName(String roleGroupName);
}
