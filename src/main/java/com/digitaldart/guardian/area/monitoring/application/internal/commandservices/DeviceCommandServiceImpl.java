package com.digitaldart.guardian.area.monitoring.application.internal.commandservices;

import com.digitaldart.guardian.area.monitoring.application.internal.outboundservices.acl.ExternalIamService;
import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.Device;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.CreateDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.services.DeviceCommandService;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import com.digitaldart.guardian.area.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceCommandServiceImpl implements DeviceCommandService {

    private final DeviceRepository deviceRepository;
    private final ExternalIamService externalIamService;

    public DeviceCommandServiceImpl(DeviceRepository deviceRepository, ExternalIamService externalIamService) {
        this.deviceRepository = deviceRepository;
        this.externalIamService = externalIamService;
    }

    @Override
    public Optional<Device> handle(CreateDeviceCommand command) {
        var userId = externalIamService.fetchUserIdByUsername(command.username());
        if (userId.isEmpty()) {
            throw new ResourceNotFoundException("Username not found");
        }
        var device = new Device(userId.get(), command);
        deviceRepository.save(device);
        return deviceRepository.findByGuardianAreaDeviceRecordId(device.getGuardianAreaDeviceRecordId());
    }
}
