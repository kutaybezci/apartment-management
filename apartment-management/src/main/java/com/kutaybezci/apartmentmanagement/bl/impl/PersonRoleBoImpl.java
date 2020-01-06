package com.kutaybezci.apartmentmanagement.bl.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.kutaybezci.apartmentmanagement.bl.PersonRoleBO;
import com.kutaybezci.apartmentmanagement.dal.PersonRoleGroupRepository;
import com.kutaybezci.apartmentmanagement.dto.RoleGroup;
import com.kutaybezci.apartmentmanagement.model.PersonEntity;
import com.kutaybezci.apartmentmanagement.model.PersonRoleGroupEntity;
import com.kutaybezci.apartmentmanagement.model.RoleGroupEntity;

@Service
public class PersonRoleBoImpl implements PersonRoleBO {
    @Autowired
    private PersonRoleGroupRepository personRoleGroupRepository;
    @Autowired
    private RoleGroupBoImpl roleGroupBoImpl;
    @Autowired
    private PersonBoImpl personBoImpl;
    @Autowired
    private ConversionService conversionService;

    @Override
    @Transactional
    public void doCreate(String username, String role) {
	PersonEntity personEntity = personBoImpl.findEntity(username);
	RoleGroupEntity roleGroupEntity = roleGroupBoImpl.findEntity(role);
	Optional<PersonRoleGroupEntity> optionalPersonRoleEntity = personRoleGroupRepository
		.findByPersonAndRoleGroup(personEntity, roleGroupEntity);
	if (optionalPersonRoleEntity.isPresent()) {
	    throw new RuntimeException("personRoleGroup.already.exists");
	}
	PersonRoleGroupEntity personRoleEntity = new PersonRoleGroupEntity();
	personRoleEntity.setPerson(personEntity);
	personRoleEntity.setRoleGroup(roleGroupEntity);
	personRoleGroupRepository.save(personRoleEntity);
    }

    @Override
    @Transactional
    public List<RoleGroup> doRead(String username) {
	return read(username);
    }

    public List<RoleGroup> read(String username) {
	PersonEntity personEntity = personBoImpl.findEntity(username);
	List<PersonRoleGroupEntity> personRoleEntities = personRoleGroupRepository.findByPerson(personEntity);
	return personRoleEntities.stream().map(t -> conversionService.convert(t.getRoleGroup(), RoleGroup.class))
		.collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void doDelete(String username, String role) {
	PersonRoleGroupEntity personRoleEntity = findEntity(username, role);
	personRoleGroupRepository.delete(personRoleEntity);
    }

    public PersonRoleGroupEntity findEntity(String username, String role) {
	PersonEntity personEntity = personBoImpl.findEntity(username);
	RoleGroupEntity securityRoleEntity = roleGroupBoImpl.findEntity(role);
	return findEntity(personEntity, securityRoleEntity);
    }

    public PersonRoleGroupEntity findEntity(PersonEntity person, RoleGroupEntity roleGroup) {
	Optional<PersonRoleGroupEntity> personRoleEntity = personRoleGroupRepository.findByPersonAndRoleGroup(person,
		roleGroup);
	if (!personRoleEntity.isPresent()) {
	    throw new RuntimeException("personRoleGroup.not.found");
	}
	return personRoleEntity.get();
    }

}
