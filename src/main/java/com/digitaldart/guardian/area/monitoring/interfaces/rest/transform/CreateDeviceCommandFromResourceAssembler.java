package com.digitaldart.guardian.area.monitoring.interfaces.rest.transform;

import com.digitaldart.guardian.area.monitoring.domain.model.commands.CreateDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.DeviceCareModes;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.DeviceStatuses;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.CreateDeviceResource;

public class CreateDeviceCommandFromResourceAssembler {
    public static CreateDeviceCommand toCommandFromResource(CreateDeviceResource resource){
        return new CreateDeviceCommand(
                resource.nickname(),
                resource.bearer(),
                DeviceCareModes.valueOf(resource.careMode()),
                DeviceStatuses.valueOf(resource.status()),
                resource.username()
        );
    }
}
