package com.digitaldart.guardian.area.monitoring.domain.services;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.Device;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllDevicesByUserIdQuery;

import java.util.List;

public interface DeviceQueryService {
    List<Device> handle(GetAllDevicesByUserIdQuery query);
}
