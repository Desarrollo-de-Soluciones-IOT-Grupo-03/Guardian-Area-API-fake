package com.digitaldart.guardian.area.monitoring.interfaces.rest.resource;

import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GeoFenceStatuses;
import com.digitaldart.guardian.area.shared.domain.model.valueobjects.Pair;

import java.util.List;

public record UpdateGeoFenceResource(String name, String geoFenceStatus, java.util.List<Pair<Float, Float>> coordinates) {
}