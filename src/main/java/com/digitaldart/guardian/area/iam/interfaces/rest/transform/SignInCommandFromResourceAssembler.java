package com.digitaldart.guardian.area.iam.interfaces.rest.transform;

import com.digitaldart.guardian.area.iam.domain.model.commands.SignInCommand;
import com.digitaldart.guardian.area.iam.interfaces.rest.resource.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource){
        return new SignInCommand(resource.username(), resource.password());
    }
}
