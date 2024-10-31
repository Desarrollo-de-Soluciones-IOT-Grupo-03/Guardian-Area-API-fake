package com.digitaldart.guardian.area.monitoring.domain.services;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.Device;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllDevicesByUserIdQuery;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetDeviceByGuardianAreaDeviceRecordIdQuery;

import java.util.List;
import java.util.Optional;

public interface DeviceQueryService {
    List<Device> handle(GetAllDevicesByUserIdQuery query);
    Optional<Device> handle(GetDeviceByGuardianAreaDeviceRecordIdQuery query);
}
