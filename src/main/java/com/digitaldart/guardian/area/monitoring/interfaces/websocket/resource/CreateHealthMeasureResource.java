package com.digitaldart.guardian.area.monitoring.interfaces.websocket.resource;

public record CreateHealthMeasureResource(int bpm, int spo2, String guardianAreaDeviceRecordId) {
}
