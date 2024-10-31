package com.digitaldart.guardian.area.monitoring.interfaces.rest.transform;

import com.digitaldart.guardian.area.monitoring.domain.model.commands.AssignDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.UpdateDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.DeviceCareModes;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.DeviceStatuses;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.AssignDeviceResource;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.UpdateDeviceResource;

public class UpdateDeviceCommandFromResourceAssembler {
    public static UpdateDeviceCommand toCommandFromResource(String deviceRecordId, UpdateDeviceResource resource){
        return new UpdateDeviceCommand(
                new GuardianAreaDeviceRecordId(deviceRecordId),
                resource.bearer(),
                resource.deviceNickname(),
                DeviceCareModes.valueOf(resource.deviceCareModes()),
                DeviceStatuses.valueOf(resource.deviceStatuses())
        );
    }
}
