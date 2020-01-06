package com.kutaybezci.apartmentmanagement.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kutaybezci.apartmentmanagement.model.RoleGroupDetailEntity;
import com.kutaybezci.apartmentmanagement.model.RoleGroupEntity;

public interface RoleGroupDetailRepository extends CrudRepository<RoleGroupDetailEntity, Long> {
    Optional<RoleGroupDetailEntity> findByRoleGroupAndRoleCode(RoleGroupEntity roleGroupEntity, String roleCode);

    List<RoleGroupDetailEntity> findByRoleGroup(RoleGroupEntity roleGroup);
}
