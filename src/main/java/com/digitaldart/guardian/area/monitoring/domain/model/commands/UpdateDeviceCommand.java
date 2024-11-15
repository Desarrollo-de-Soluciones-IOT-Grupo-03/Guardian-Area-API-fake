package com.digitaldart.guardian.area.monitoring.domain.model.commands;

import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.DeviceCareModes;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.DeviceStatuses;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;

public record UpdateDeviceCommand(GuardianAreaDeviceRecordId guardianAreaDeviceRecordId, String bearer, String deviceNickname, DeviceCareModes deviceCareModes, DeviceStatuses deviceStatuses) {
}
