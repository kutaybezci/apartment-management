package com.kutaybezci.apartmentmanagement.bl;

import java.util.List;

public interface AuthorizationBO {
    void doCreate(String roleGroup, String roleCode);

    Role doRead(String authorization);

    void doDelete(String roleGroup, String roleCode);

    List<Role> doReadByRole(String roleGroup);
}
