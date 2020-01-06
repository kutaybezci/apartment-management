package com.kutaybezci.apartmentmanagement.convertor;

import org.springframework.core.convert.converter.Converter;

import com.kutaybezci.apartmentmanagement.dto.RoleGroup;
import com.kutaybezci.apartmentmanagement.model.RoleGroupEntity;

public class RoleGroupEntityToRoleGroup implements Converter<RoleGroupEntity, RoleGroup> {

    @Override
    public RoleGroup convert(RoleGroupEntity roleGroupEntity) {
	RoleGroup roleGroup = new RoleGroup();
	roleGroup.setRoleGroupName(roleGroupEntity.getRoleGroupName());
	roleGroup.setDescription(roleGroupEntity.getDescription());
	return roleGroup;
    }

}
