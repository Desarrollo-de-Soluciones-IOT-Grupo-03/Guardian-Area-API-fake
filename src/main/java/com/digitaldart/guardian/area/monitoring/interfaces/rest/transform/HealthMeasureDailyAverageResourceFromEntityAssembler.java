package com.digitaldart.guardian.area.monitoring.interfaces.rest.transform;

import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.HealthMeasureDailyAverage;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.HealthMeasureDailyAverageResource;

public class HealthMeasureDailyAverageResourceFromEntityAssembler {
    public static HealthMeasureDailyAverageResource toResourceFromEntity(HealthMeasureDailyAverage healthMeasureDailyAverage) {
        return new HealthMeasureDailyAverageResource(
                healthMeasureDailyAverage.getDate(),
                healthMeasureDailyAverage.getAvgBpm(),
                healthMeasureDailyAverage.getAvgSpo2()
        );
    }
}
