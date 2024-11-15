package com.digitaldart.guardian.area.monitoring.domain.model.commands;

import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.Coordinate;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GeoFenceStatuses;

import java.util.List;

public record UpdateGeoFenceCommand(Long geoFenceId, String name, GeoFenceStatuses geoFenceStatus, List<Coordinate> coordinates) {
}
