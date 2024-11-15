package com.digitaldart.guardian.area.monitoring.domain.services;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.GeoFence;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllGeoFencesByGuardianAreaDeviceRecordIdQuery;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetDeviceByGuardianAreaDeviceRecordIdQuery;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetGeoFenceByIdQuery;

import java.util.List;
import java.util.Optional;

public interface GeoFenceQueryService {
    List<GeoFence> handle(GetAllGeoFencesByGuardianAreaDeviceRecordIdQuery query);
    Optional<GeoFence> handle(GetGeoFenceByIdQuery query);
}
