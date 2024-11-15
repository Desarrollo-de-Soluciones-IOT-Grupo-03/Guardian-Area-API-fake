package com.digitaldart.guardian.area.monitoring.interfaces.websocket.resource;

public record CurrentLocationResource(float latitude, float longitude, String guardianAreaDeviceRecordId) {
}
