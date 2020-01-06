package com.kutaybezci.apartmentmanagement.dal;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kutaybezci.apartmentmanagement.model.PersonEntity;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByUsername(String username);

    Optional<PersonEntity> findByEmail(String email);

    Optional<PersonEntity> findByPhone(String phone);
}
