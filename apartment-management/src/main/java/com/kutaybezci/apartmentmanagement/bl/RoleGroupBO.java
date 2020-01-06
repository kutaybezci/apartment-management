package com.kutaybezci.apartmentmanagement.bl;

import com.kutaybezci.apartmentmanagement.dto.RoleGroup;

public interface RoleGroupBO {
    void doCreate(RoleGroup roleGroup);

    RoleGroup doRead(String name);

    void doUpdate(RoleGroup roleGroup);

    void doUpdateRoleGroupName(String oldName, String newName);
}
