package com.kutaybezci.apartmentmanagement.bl.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kutaybezci.apartmentmanagement.bl.PersonBO;
import com.kutaybezci.apartmentmanagement.bl.Role;
import com.kutaybezci.apartmentmanagement.dal.PersonRepository;
import com.kutaybezci.apartmentmanagement.dto.Person;
import com.kutaybezci.apartmentmanagement.dto.RoleGroup;
import com.kutaybezci.apartmentmanagement.model.PersonEntity;

@Service
public class PersonBoImpl implements PersonBO {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthorizationBoImpl authorizationBoImpl;
    @Autowired
    private PersonRoleBoImpl personRoleBoImpl;

    public enum DefaultUser {
	SYSTEM(false), ADMIN(true);

	public boolean hasPassword;

	private DefaultUser(boolean hasPassword) {
	    this.hasPassword = hasPassword;
	}
    }

    @Override
    @Transactional
    public void doCreate(Person user) {
	create(user);
    }

    @Override
    @Transactional
    public void doCreateDefaultUsers() {
	for (DefaultUser defaultUser : DefaultUser.values()) {
	    Optional<Person> user = read(defaultUser.name());
	    if (!user.isPresent()) {
		Person newUser = new Person();
		if (defaultUser.hasPassword) {
		    newUser.setPassword(defaultUser.name());
		}
		newUser.setUsername(defaultUser.name());
		create(newUser);
	    }
	}
    }

    @Override
    @Transactional
    public Person doRead(String username) {
	PersonEntity personEntity = findEntity(username);
	return conversionService.convert(personEntity, Person.class);
    }

    @Override
    @Transactional
    public Person doFind(String usernameOrPhoneOrMail) {
	Optional<PersonEntity> personEntity;
	if (StringUtils.contains(usernameOrPhoneOrMail, "@")) {
	    personEntity = personRepository.findByEmail(usernameOrPhoneOrMail);
	} else if (StringUtils.isNumeric(usernameOrPhoneOrMail)) {
	    personEntity = personRepository.findByPhone(usernameOrPhoneOrMail);
	} else {
	    personEntity = personRepository.findByUsername(usernameOrPhoneOrMail);
	}
	if (personEntity.isPresent()) {
	    return conversionService.convert(personEntity.get(), Person.class);
	}
	throw new RuntimeException("user.not.found");
    }

    @Override
    @Transactional
    public void doUpdate(Person user) {
	PersonEntity personEntity = findEntity(user.getUsername());
	personEntity.setEmail(user.getEmail());
	personEntity.setFullname(user.getFullname());
	personEntity.setPhone(user.getPhone());
	personRepository.save(personEntity);
    }

    @Override
    @Transactional
    public void doUpdateUsername(String oldUsername, String newUsername) {
	PersonEntity personEntity = findEntity(oldUsername);
	personEntity.setUsername(newUsername);
	personRepository.save(personEntity);
    }

    protected void create(Person user) {
	if (StringUtils.isNoneBlank(user.getPassword())) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	}
	PersonEntity personEntity = conversionService.convert(user, PersonEntity.class);
	personRepository.save(personEntity);
    }

    protected Optional<Person> read(String username) {
	Optional<PersonEntity> personEntity = personRepository.findByUsername(username);
	if (personEntity.isPresent()) {
	    return Optional.of(conversionService.convert(personEntity.get(), Person.class));
	} else {
	    return Optional.empty();
	}
    }

    @Override
    @Transactional
    public void doUpdatePassword(String username, String newPassword) {
	PersonEntity personEntity = findEntity(username);
	if (StringUtils.isNoneBlank(newPassword)) {
	    newPassword = bCryptPasswordEncoder.encode(newPassword);
	}
	personEntity.setPassword(newPassword);
    }

    public PersonEntity findEntity(String username) {
	Optional<PersonEntity> personEntity = personRepository.findByUsername(username);
	if (!personEntity.isPresent()) {
	    throw new RuntimeException("user.not.found");
	}
	return personEntity.get();
    }

    @Override
    @Transactional
    public List<Role> doGetAuthorizationList(String username) {
	PersonEntity personEntity = findEntity(username);
	List<RoleGroup> roleGroups = personRoleBoImpl.read(personEntity.getUsername());
	return roleGroups.stream().flatMap(t -> authorizationBoImpl.readByRole(t.getRoleGroupName()).stream())
		.distinct().collect(Collectors.toList());

    }

}
