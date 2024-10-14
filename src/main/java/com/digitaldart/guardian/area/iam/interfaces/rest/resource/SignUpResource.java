package com.digitaldart.guardian.area.iam.interfaces.rest.resource;

import java.util.List;

public record SignUpResource(String username, String password, List<String> roles) {
}
