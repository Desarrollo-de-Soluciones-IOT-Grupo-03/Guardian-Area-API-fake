package com.digitaldart.guardian.area.monitoring.domain.services;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.Device;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllDevicesByUsernameQuery;

import java.util.List;

public interface DeviceQueryService {
    List<Device> handle(GetAllDevicesByUsernameQuery query);
}
