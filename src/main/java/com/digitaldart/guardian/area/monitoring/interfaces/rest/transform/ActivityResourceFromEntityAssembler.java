package com.digitaldart.guardian.area.monitoring.interfaces.rest.transform;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.Activity;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.ActivityResource;

public class ActivityResourceFromEntityAssembler {
    public static ActivityResource toResourceFromEntity(Activity activity){
        return new ActivityResource(
                activity.getGuardianAreaDeviceRecordId().deviceRecordId(),
                activity.getActivityEventName().toString(),
                activity.getActivityType().toString(),
                activity.getCreatedAt()
        );
    }
}
