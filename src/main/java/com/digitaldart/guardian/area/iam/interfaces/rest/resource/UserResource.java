package com.digitaldart.guardian.area.iam.interfaces.rest.resource;

import java.util.List;

public record UserResource(Long id, String username, String email, String firstName, String lastName, List<String> roles) {
}
