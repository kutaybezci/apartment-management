package com.kutaybezci.apartmentmanagement.bl.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kutaybezci.apartmentmanagement.bl.PersonBO;
import com.kutaybezci.apartmentmanagement.bl.Role;
import com.kutaybezci.apartmentmanagement.dto.Person;

public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private PersonBO personBO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Person user = personBO.doFind(username);
	String[] authorizations = personBO.doGetAuthorizationList(user.getUsername()).stream().map(Role::name)
		.toArray(String[]::new);
	return User.withUsername(user.getUsername()).password(user.getPassword()).roles(authorizations).build();
    }

}
