package com.digitaldart.guardian.area.iam.interfaces.rest.transform;

import com.digitaldart.guardian.area.iam.domain.model.aggregates.User;
import com.digitaldart.guardian.area.iam.domain.model.entities.Role;
import com.digitaldart.guardian.area.iam.interfaces.rest.resource.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        var roles = entity.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(entity.getId(), entity.getUsername(), entity.getEmail().address(), entity.getPersonName().firstName(), entity.getPersonName().lastName(), roles);
    }
}
