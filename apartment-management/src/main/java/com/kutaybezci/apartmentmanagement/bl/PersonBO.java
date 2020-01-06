package com.kutaybezci.apartmentmanagement.bl;

import java.util.List;

import com.kutaybezci.apartmentmanagement.dto.Person;

public interface PersonBO {
    void doCreate(Person user);

    Person doRead(String username);

    Person doFind(String usernameOrPhoneOrMail);

    void doUpdate(Person user);

    void doUpdateUsername(String oldUsername, String newUsername);

    void doUpdatePassword(String username, String newPassword);

    void doCreateDefaultUsers();

    List<Role> doGetAuthorizationList(String username);

}
