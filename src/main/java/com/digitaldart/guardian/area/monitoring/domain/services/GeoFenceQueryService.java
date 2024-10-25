package com.digitaldart.guardian.area.monitoring.domain.services;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.GeoFence;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllGeoFencesByGuardianAreaDeviceRecordIdQuery;

import java.util.List;

public interface GeoFenceQueryService {
    List<GeoFence> handle(GetAllGeoFencesByGuardianAreaDeviceRecordIdQuery query);
}
