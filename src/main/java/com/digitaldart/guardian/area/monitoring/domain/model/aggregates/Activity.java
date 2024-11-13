package com.digitaldart.guardian.area.monitoring.domain.model.aggregates;

import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.ActivityType;
import com.digitaldart.guardian.area.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class Activity extends AuditableAbstractAggregateRoot<Activity> {
    @Getter
    private String eventName;

    @Getter
    private ActivityType activityType;
}
