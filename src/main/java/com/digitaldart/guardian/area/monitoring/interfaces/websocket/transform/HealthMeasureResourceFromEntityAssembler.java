package com.digitaldart.guardian.area.monitoring.interfaces.websocket.transform;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.HealthMeasure;
import com.digitaldart.guardian.area.monitoring.interfaces.websocket.resource.HealthMeasureResource;

public class HealthMeasureResourceFromEntityAssembler {
    public static HealthMeasureResource toResourceFromEntity(HealthMeasure healthMeasure){
        return new HealthMeasureResource(
                healthMeasure.getBpm().bpm(),
                healthMeasure.getSpo2().spo2()
        );
    }
}
