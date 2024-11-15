package com.digitaldart.guardian.area.monitoring.domain.model.commands;

import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.ActivityEventName;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.ActivityType;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;

public record CreateActivityCommand(GuardianAreaDeviceRecordId guardianAreaDeviceRecordId, ActivityEventName activityEventName, ActivityType activityType) {
}
