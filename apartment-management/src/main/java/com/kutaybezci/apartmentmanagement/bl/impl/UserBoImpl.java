package com.kutaybezci.apartmentmanagement.bl.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kutaybezci.apartmentmanagement.bl.UserBO;
import com.kutaybezci.apartmentmanagement.dal.UserAccountRepository;
import com.kutaybezci.apartmentmanagement.dto.BasicRequest;
import com.kutaybezci.apartmentmanagement.dto.CreateUserRequest;
import com.kutaybezci.apartmentmanagement.model.UserAccount;

@Service
public class UserBoImpl implements UserBO {
    @Autowired
    private UserAccountRepository userAccountRepository;

    public static final String SYSTEM_USER = "SYSTEM";

    @Override
    @Transactional
    public String doCreateUser(CreateUserRequest request) {
	if (StringUtils.isBlank(request.getUsername())) {
	    throw new RuntimeException("username.empty");
	}
	if (StringUtils.contains(request.getUsername(), "@") || StringUtils.isNumericSpace(request.getUsername())) {
	    throw new RuntimeException("username.not.valid");
	}
	if (StringUtils.isNoneBlank(request.getEmail()) && StringUtils.containsNone(request.getEmail(), "@")) {
	    throw new RuntimeException("email.not.valid");
	}
	if (StringUtils.isNoneBlank(request.getPhone()) && !StringUtils.isNumeric(request.getPhone())) {
	    throw new RuntimeException("phone.not.valid");
	}
	UserAccount userAccount = new UserAccount();
	userAccount.setEmail(StringUtils.lowerCase(request.getEmail()));
	userAccount.setFullname(request.getFullname().trim());
	userAccount.setPassword(request.getPassword().trim());
	userAccount.setPhone(request.getPhone().trim());
	userAccount.setUsername(StringUtils.upperCase(request.getUsername().trim()));
	userAccountRepository.save(userAccount);
	return userAccount.getUsername();
    }

    @Override
    public boolean createSystemUser() {
	Optional<UserAccount> systemUser = userAccountRepository.findByUsername(SYSTEM_USER);
	if (!systemUser.isPresent()) {
	    UserAccount userAccount = new UserAccount();
	    userAccount.setUsername(SYSTEM_USER);
	    userAccount.setPassword(SYSTEM_USER);
	    userAccountRepository.save(userAccount);
	    return true;
	}
	return false;
    }

    @Override
    @Transactional
    public boolean doLogin(BasicRequest request) {
	try {
	    login(request);
	    return true;
	} catch (Exception ex) {
	    return false;
	}
    }

    public UserAccount login(BasicRequest request) {
	if (StringUtils.isAnyBlank(request.getLoginName(), request.getLoginPassword())) {
	    throw new RuntimeException("user.name or user.password empty");
	}
	Optional<UserAccount> userOptional;
	if (StringUtils.contains(request.getLoginName(), "@")) {
	    userOptional = userAccountRepository.findByEmail(request.getLoginName());
	} else if (StringUtils.isNumeric(request.getLoginName())) {
	    userOptional = userAccountRepository.findByPhone(request.getLoginName());
	} else {
	    userOptional = userAccountRepository.findByUsername(request.getLoginName());
	}
	if (!userOptional.isPresent()) {
	    throw new RuntimeException("username not exist");
	}
	if (StringUtils.equals(userOptional.get().getPassword(), request.getLoginPassword())) {
	    return userOptional.get();
	} else {
	    throw new RuntimeException("password not match");
	}
    }

}
