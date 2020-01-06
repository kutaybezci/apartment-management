package com.kutaybezci.apartmentmanagement.convertor;

import org.springframework.core.convert.converter.Converter;

import com.kutaybezci.apartmentmanagement.dto.Person;
import com.kutaybezci.apartmentmanagement.model.PersonEntity;

public class PersonEntityToPerson implements Converter<PersonEntity, Person> {

    @Override
    public Person convert(PersonEntity personEntity) {
	Person person = new Person();
	person.setEmail(personEntity.getEmail());
	person.setFullname(personEntity.getFullname());
	person.setPassword(personEntity.getPassword());
	person.setPhone(personEntity.getPassword());
	person.setUsername(personEntity.getUsername());
	return person;
    }

}
