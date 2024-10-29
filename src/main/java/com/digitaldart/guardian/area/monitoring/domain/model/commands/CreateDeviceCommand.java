package com.digitaldart.guardian.area.monitoring.domain.model.commands;

import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.DeviceCareModes;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.DeviceStatuses;

public record CreateDeviceCommand(String nickname, String bearer, DeviceCareModes careMode, DeviceStatuses status, String username) {
}
