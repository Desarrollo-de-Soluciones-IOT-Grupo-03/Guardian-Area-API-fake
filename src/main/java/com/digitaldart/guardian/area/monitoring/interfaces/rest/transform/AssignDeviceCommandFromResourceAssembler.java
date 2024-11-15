package com.digitaldart.guardian.area.monitoring.interfaces.rest.transform;

import com.digitaldart.guardian.area.monitoring.domain.model.commands.AssignDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.AssignDeviceResource;

public class AssignDeviceCommandFromResourceAssembler {
    public static AssignDeviceCommand toCommandFromResource(AssignDeviceResource resource){
        return new AssignDeviceCommand(
                new GuardianAreaDeviceRecordId(resource.guardianAreaDeviceRecordId()),
                resource.userId()
        );
    }
}
