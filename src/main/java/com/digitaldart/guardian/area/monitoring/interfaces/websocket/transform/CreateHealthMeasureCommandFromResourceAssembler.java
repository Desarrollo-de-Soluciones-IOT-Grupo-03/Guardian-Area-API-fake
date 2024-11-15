package com.digitaldart.guardian.area.monitoring.interfaces.websocket.transform;

import com.digitaldart.guardian.area.monitoring.domain.model.commands.CreateHealthMeasureCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.ApiKey;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.BeatsPerMinute;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.SaturationOfPeripheralOxygen;
import com.digitaldart.guardian.area.monitoring.interfaces.websocket.resource.CreateHealthMeasureResource;

public class CreateHealthMeasureCommandFromResourceAssembler {
    public static CreateHealthMeasureCommand toCommandFromResource(String apiKey, CreateHealthMeasureResource resource) {
        return new CreateHealthMeasureCommand(
                new BeatsPerMinute(resource.bpm()),
                new SaturationOfPeripheralOxygen(resource.spo2()),
                new GuardianAreaDeviceRecordId(resource.guardianAreaDeviceRecordId()),
                new ApiKey(apiKey)
        );
    }
}
