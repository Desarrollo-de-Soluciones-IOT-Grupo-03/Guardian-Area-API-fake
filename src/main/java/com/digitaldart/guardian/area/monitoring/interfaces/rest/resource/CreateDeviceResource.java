package com.digitaldart.guardian.area.monitoring.interfaces.rest.resource;

public record CreateDeviceResource(String nickname, String bearer, String careMode, String status, String username) {
}
