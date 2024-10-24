package com.digitaldart.guardian.area.iam.interfaces.rest.transform;

import com.digitaldart.guardian.area.iam.domain.model.aggregates.User;
import com.digitaldart.guardian.area.iam.interfaces.rest.resource.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token){
        return new AuthenticatedUserResource(entity.getId(), entity.getUsername(), token);
    }
}
