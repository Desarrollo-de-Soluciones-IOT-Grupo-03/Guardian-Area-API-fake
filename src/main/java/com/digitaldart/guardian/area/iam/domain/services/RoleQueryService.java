package com.digitaldart.guardian.area.iam.domain.services;

import com.digitaldart.guardian.area.iam.domain.model.aggregates.User;
import com.digitaldart.guardian.area.iam.domain.model.entities.Role;
import com.digitaldart.guardian.area.iam.domain.model.queries.GetAllRolesQuery;
import com.digitaldart.guardian.area.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<User> handle(GetRoleByNameQuery query);
}
