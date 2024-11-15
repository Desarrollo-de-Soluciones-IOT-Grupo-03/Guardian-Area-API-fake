package com.digitaldart.guardian.area.monitoring.interfaces.rest.transform;

import com.digitaldart.guardian.area.monitoring.domain.model.commands.RegisterDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.RegisterDeviceResource;

public class RegisterDeviceCommandFromResourceAssembler {
    public static RegisterDeviceCommand toCommandFromResource(RegisterDeviceResource resource) {
        return new RegisterDeviceCommand(
                new GuardianAreaDeviceRecordId(resource.guardianAreaDeviceRecordId())
        );
    }
}
