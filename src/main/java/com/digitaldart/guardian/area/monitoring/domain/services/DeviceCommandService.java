package com.digitaldart.guardian.area.monitoring.domain.services;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.Device;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.AssignDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.RegisterDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.UpdateDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.ApiKey;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface DeviceCommandService {
    Optional<Device> handle(AssignDeviceCommand command);
    Optional<String> handle(RegisterDeviceCommand command);
    Optional<Device> handle(UpdateDeviceCommand command);
}
