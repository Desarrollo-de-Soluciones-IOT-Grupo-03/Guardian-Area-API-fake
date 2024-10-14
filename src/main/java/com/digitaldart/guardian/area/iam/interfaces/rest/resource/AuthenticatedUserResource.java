package com.digitaldart.guardian.area.iam.interfaces.rest.resource;

public record AuthenticatedUserResource(Long id, String username, String token) {
}
