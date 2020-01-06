package com.kutaybezci.apartmentmanagement.bl;

import java.util.List;

import com.kutaybezci.apartmentmanagement.dto.RoleGroup;

public interface PersonRoleBO {
    void doCreate(String username, String role);

    List<RoleGroup> doRead(String username);

    void doDelete(String username, String role);
}
