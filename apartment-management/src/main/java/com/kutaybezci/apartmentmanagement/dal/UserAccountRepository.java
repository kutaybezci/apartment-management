package com.kutaybezci.apartmentmanagement.dal;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kutaybezci.apartmentmanagement.model.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);

    Optional<UserAccount> findByEmail(String email);

    Optional<UserAccount> findByPhone(String phone);
}
