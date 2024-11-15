package com.digitaldart.guardian.area.monitoring.domain.services;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.Activity;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllActivitiesByGuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllActivitiesByGuardianAreaDeviceRecordIdAndActivityType;

import java.util.List;

public interface ActivityQueryService {
    List<Activity> handle(GetAllActivitiesByGuardianAreaDeviceRecordId query);
    List<Activity> handle(GetAllActivitiesByGuardianAreaDeviceRecordIdAndActivityType query);
}
