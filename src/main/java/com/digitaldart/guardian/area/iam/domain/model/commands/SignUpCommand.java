package com.digitaldart.guardian.area.iam.domain.model.commands;

import com.digitaldart.guardian.area.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String email, String firstName, String lastName, String password, List<Role> roles) {
}
