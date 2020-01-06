package com.kutaybezci.apartmentmanagement.bl.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.kutaybezci.apartmentmanagement.bl.RoleGroupBO;
import com.kutaybezci.apartmentmanagement.dal.RoleGroupRepository;
import com.kutaybezci.apartmentmanagement.dto.RoleGroup;
import com.kutaybezci.apartmentmanagement.model.RoleGroupEntity;

@Service
public class RoleGroupBoImpl implements RoleGroupBO {
    @Autowired
    private RoleGroupRepository roleGroupRepository;
    @Autowired
    private ConversionService conversionService;

    @Override
    @Transactional
    public void doCreate(RoleGroup securityRole) {
	Optional<RoleGroup> optionalSecurityRole = read(securityRole.getRoleGroupName());
	if (optionalSecurityRole.isPresent()) {
	    throw new RuntimeException("roleGroup.already.exist");
	}
	RoleGroupEntity securityRoleEntity = conversionService.convert(securityRole, RoleGroupEntity.class);
	roleGroupRepository.save(securityRoleEntity);
    }

    @Override
    @Transactional
    public RoleGroup doRead(String name) {
	RoleGroupEntity securityRoleEntity = findEntity(name);
	return conversionService.convert(securityRoleEntity, RoleGroup.class);
    }

    @Override
    @Transactional
    public void doUpdate(RoleGroup securityRole) {
	RoleGroupEntity securityRoleEntity = findEntity(securityRole.getRoleGroupName());
	securityRoleEntity.setRoleGroupName(securityRole.getRoleGroupName());
	roleGroupRepository.save(securityRoleEntity);
    }

    @Override
    @Transactional
    public void doUpdateRoleGroupName(String oldName, String newName) {
	RoleGroupEntity securityRoleEntity = findEntity(oldName);
	securityRoleEntity.setRoleGroupName(newName);
	roleGroupRepository.save(securityRoleEntity);
    }

    public Optional<RoleGroup> read(String name) {
	Optional<RoleGroupEntity> roleGroupEntity = roleGroupRepository.findByRoleGroupName(name);
	if (roleGroupEntity.isPresent()) {
	    return Optional.of(conversionService.convert(roleGroupEntity, RoleGroup.class));
	}
	return Optional.empty();
    }

    public RoleGroupEntity findEntity(String name) {
	Optional<RoleGroupEntity> optionalSecurityRoleEntity = roleGroupRepository.findByRoleGroupName(name);
	if (!optionalSecurityRoleEntity.isPresent()) {
	    throw new RuntimeException("roleGroup.not.found");
	}
	return optionalSecurityRoleEntity.get();
    }

}
