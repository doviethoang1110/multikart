package com.hoang.projections;

import com.hoang.entities.Permissions;

import java.util.List;
import java.util.Set;

public interface RoleProjections {
    Integer getId();
    String getName();
    String getDisplayName();
    String getPermissions();
}
