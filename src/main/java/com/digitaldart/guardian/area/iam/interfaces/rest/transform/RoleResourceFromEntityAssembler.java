package com.digitaldart.guardian.area.iam.interfaces.rest.transform;

import com.digitaldart.guardian.area.iam.domain.model.entities.Role;
import com.digitaldart.guardian.area.iam.interfaces.rest.resource.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource roleResourceFromEntity(Role entity) {
        return new RoleResource(entity.getId(), entity.getStringName());
    }
}
