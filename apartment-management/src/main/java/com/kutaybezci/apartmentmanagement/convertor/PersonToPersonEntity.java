package com.kutaybezci.apartmentmanagement.convertor;

import org.springframework.core.convert.converter.Converter;

import com.kutaybezci.apartmentmanagement.dto.Person;
import com.kutaybezci.apartmentmanagement.model.PersonEntity;

public class PersonToPersonEntity implements Converter<Person, PersonEntity> {

    @Override
    public PersonEntity convert(Person person) {
	PersonEntity personEntity = new PersonEntity();
	personEntity.setEmail(person.getEmail());
	personEntity.setFullname(person.getFullname());
	personEntity.setPassword(person.getPassword());
	personEntity.setPhone(person.getPhone());
	personEntity.setUsername(person.getUsername());
	return personEntity;
    }

}
