package com.digitaldart.guardian.area.monitoring.interfaces.rest.resource;

import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.DeviceCareModes;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.DeviceStatuses;

public record UpdateDeviceResource(String bearer, String deviceNickname, String deviceCareModes, String deviceStatuses) {
}
