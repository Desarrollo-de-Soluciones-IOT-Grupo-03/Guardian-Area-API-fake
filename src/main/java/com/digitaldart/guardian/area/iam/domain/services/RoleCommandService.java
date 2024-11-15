package com.digitaldart.guardian.area.iam.domain.services;

import com.digitaldart.guardian.area.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
