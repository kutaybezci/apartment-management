package com.kutaybezci.apartmentmanagement.bl.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kutaybezci.apartmentmanagement.bl.AuthorizationBO;
import com.kutaybezci.apartmentmanagement.bl.Role;
import com.kutaybezci.apartmentmanagement.dal.RoleGroupDetailRepository;
import com.kutaybezci.apartmentmanagement.model.RoleGroupDetailEntity;
import com.kutaybezci.apartmentmanagement.model.RoleGroupEntity;

@Service
public class AuthorizationBoImpl implements AuthorizationBO {
    @Autowired
    private RoleGroupBoImpl securityRoleBoImpl;
    @Autowired
    private RoleGroupDetailRepository roleGroupDetailRepository;

    @Override
    @Transactional
    public void doCreate(String roleGroupName, String roleCode) {
	RoleGroupEntity roleGroupEntity = securityRoleBoImpl.findEntity(roleGroupName);
	Optional<RoleGroupDetailEntity> roleGroupDetailEntity = roleGroupDetailRepository
		.findByRoleGroupAndRoleCode(roleGroupEntity, roleCode);
	if (roleGroupDetailEntity.isPresent()) {
	    throw new RuntimeException("roleGroupDetail.already.exist");
	}
	RoleGroupDetailEntity roleGroupDetail = new RoleGroupDetailEntity();
	roleGroupDetail.setRoleGroup(roleGroupEntity);
	roleGroupDetail.setRoleCode(roleCode);
	roleGroupDetailRepository.save(roleGroupDetail);
    }

    @Override
    public Role doRead(String roleCode) {
	try {
	    return Role.valueOf(roleCode);
	} catch (Exception ex) {
	    throw new RuntimeException("role.not.found");
	}
    }

    @Override
    @Transactional
    public List<Role> doReadByRole(String role) {
	return readByRole(role);
    }

    public List<Role> readByRole(String role) {
	RoleGroupEntity securityRoleEntity = securityRoleBoImpl.findEntity(role);
	List<RoleGroupDetailEntity> roleAuthorizationList = roleGroupDetailRepository
		.findByRoleGroup(securityRoleEntity);
	return roleAuthorizationList.stream().map(t -> doRead(t.getRoleCode())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void doDelete(String roleGroup, String roleCode) {
	RoleGroupEntity securityRoleEntity = securityRoleBoImpl.findEntity(roleGroup);
	RoleGroupDetailEntity roleAuthorizationEntity = findEntity(securityRoleEntity, roleCode);
	roleGroupDetailRepository.delete(roleAuthorizationEntity);
    }

    public RoleGroupDetailEntity findEntity(RoleGroupEntity roleGroupEntity, String authorization) {
	Optional<RoleGroupDetailEntity> roleAuthorizationEntity = roleGroupDetailRepository
		.findByRoleGroupAndRoleCode(roleGroupEntity, authorization);
	if (!roleAuthorizationEntity.isPresent()) {
	    throw new RuntimeException("authorization.not.found");
	}
	return roleAuthorizationEntity.get();
    }

}
