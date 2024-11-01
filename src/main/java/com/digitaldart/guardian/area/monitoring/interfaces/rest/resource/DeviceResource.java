package com.digitaldart.guardian.area.monitoring.interfaces.rest.resource;

public record DeviceResource(String guardianAreaDeviceRecordId, String nickname, String bearer, String careMode, String status, Long userId, String apiKey) {
}
