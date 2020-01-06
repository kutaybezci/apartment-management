package com.kutaybezci.apartmentmanagement.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kutaybezci.apartmentmanagement.model.PersonEntity;
import com.kutaybezci.apartmentmanagement.model.PersonRoleGroupEntity;
import com.kutaybezci.apartmentmanagement.model.RoleGroupEntity;

public interface PersonRoleGroupRepository extends CrudRepository<PersonRoleGroupEntity, Long> {
    Optional<PersonRoleGroupEntity> findByPersonAndRoleGroup(PersonEntity person, RoleGroupEntity roleGroup);

    List<PersonRoleGroupEntity> findByPerson(PersonEntity person);
}
