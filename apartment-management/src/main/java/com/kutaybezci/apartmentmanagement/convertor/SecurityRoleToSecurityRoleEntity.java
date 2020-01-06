package com.kutaybezci.apartmentmanagement.convertor;

import org.springframework.core.convert.converter.Converter;

import com.kutaybezci.apartmentmanagement.dto.RoleGroup;
import com.kutaybezci.apartmentmanagement.model.RoleGroupEntity;

public class SecurityRoleToSecurityRoleEntity implements Converter<RoleGroup, RoleGroupEntity> {

    @Override
    public RoleGroupEntity convert(RoleGroup roleGroup) {
	RoleGroupEntity roleGroupEntity = new RoleGroupEntity();
	roleGroupEntity.setRoleGroupName(roleGroup.getRoleGroupName());
	roleGroupEntity.setDescription(roleGroup.getDescription());
	return roleGroupEntity;
    }

}
