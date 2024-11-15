package com.digitaldart.guardian.area.monitoring.domain.model.aggregates;

import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.ActivityEventName;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.ActivityType;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Entity
public class Activity extends AuditableAbstractAggregateRoot<Activity> {
    @Getter
    private GuardianAreaDeviceRecordId guardianAreaDeviceRecordId;

    @Getter
    @Enumerated(EnumType.STRING)
    private ActivityEventName activityEventName;

    @Getter
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    public Activity() {}

    public Activity(GuardianAreaDeviceRecordId guardianAreaDeviceRecordId, ActivityEventName activityEventName, ActivityType activityType) {
        this.guardianAreaDeviceRecordId = guardianAreaDeviceRecordId;
        this.activityEventName = activityEventName;
        this.activityType = activityType;
    }
}
