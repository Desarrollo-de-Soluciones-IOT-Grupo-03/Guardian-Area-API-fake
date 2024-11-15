package com.digitaldart.guardian.area.iam.interfaces.rest.transform;

import com.digitaldart.guardian.area.iam.domain.model.commands.SignUpCommand;
import com.digitaldart.guardian.area.iam.domain.model.entities.Role;
import com.digitaldart.guardian.area.iam.interfaces.rest.resource.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource){
        var roles = resource.roles() !=null
                ? resource.roles().stream().map(Role::toRoleFromName).toList()
                : new ArrayList<Role>();
        return new SignUpCommand(resource.username(), resource.email(), resource.firstName(), resource.lastName(), resource.password(), roles);
    }
}
