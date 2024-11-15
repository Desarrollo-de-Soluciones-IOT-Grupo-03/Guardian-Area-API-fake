package com.digitaldart.guardian.area.monitoring.interfaces.rest.resource;

import com.digitaldart.guardian.area.shared.domain.model.valueobjects.Pair;

import java.util.List;

public record GeoFenceResource(Long id, String name, String geoFenceStatus, List<Pair<Float, Float>> coordinates, String guardianAreaDeviceRecordId) {
}
