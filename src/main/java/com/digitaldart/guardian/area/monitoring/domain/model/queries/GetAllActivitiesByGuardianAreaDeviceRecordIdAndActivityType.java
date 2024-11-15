package com.digitaldart.guardian.area.monitoring.domain.model.queries;

import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.ActivityType;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;

public record GetAllActivitiesByGuardianAreaDeviceRecordIdAndActivityType(GuardianAreaDeviceRecordId guardianAreaDeviceRecordId, ActivityType activityType) {
}
