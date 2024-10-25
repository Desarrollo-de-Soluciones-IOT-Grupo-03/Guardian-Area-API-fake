package com.digitaldart.guardian.area.monitoring.domain.model.commands;

import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.Coordinate;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GeoFenceStatuses;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;

import java.util.List;

public record CreateGeoFenceCommand(GuardianAreaDeviceRecordId guardianAreaDeviceRecordId, String name, GeoFenceStatuses geoFenceStatus, List<Coordinate> coordinates) {
}
