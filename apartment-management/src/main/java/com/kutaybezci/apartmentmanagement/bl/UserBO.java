package com.kutaybezci.apartmentmanagement.bl;

import com.kutaybezci.apartmentmanagement.dto.BasicRequest;
import com.kutaybezci.apartmentmanagement.dto.CreateUserRequest;

public interface UserBO {
    String doCreateUser(CreateUserRequest request);

    boolean createSystemUser();

    boolean doLogin(BasicRequest basicRequest);
}
