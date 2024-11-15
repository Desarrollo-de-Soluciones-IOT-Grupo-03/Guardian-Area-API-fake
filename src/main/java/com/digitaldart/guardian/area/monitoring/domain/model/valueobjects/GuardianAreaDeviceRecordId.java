package com.digitaldart.guardian.area.monitoring.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record GuardianAreaDeviceRecordId(String deviceRecordId) {
    public GuardianAreaDeviceRecordId() { this(UUID.randomUUID().toString()); }

    public GuardianAreaDeviceRecordId {
        if (deviceRecordId == null || deviceRecordId.isBlank()) {
            throw new IllegalArgumentException("Acme device record userId cannot be null or blank");
        }
    }
}
