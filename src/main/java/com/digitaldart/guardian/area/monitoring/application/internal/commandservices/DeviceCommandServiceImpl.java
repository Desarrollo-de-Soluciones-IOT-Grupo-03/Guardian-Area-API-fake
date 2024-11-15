package com.digitaldart.guardian.area.monitoring.application.internal.commandservices;

import com.digitaldart.guardian.area.monitoring.application.internal.outboundservices.SecureApiKeyGenerator;
import com.digitaldart.guardian.area.monitoring.application.internal.outboundservices.acl.ExternalIamService;
import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.Device;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.AssignDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.RegisterDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.UpdateDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.domain.services.DeviceCommandService;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import com.digitaldart.guardian.area.shared.domain.exceptions.ResourceNotFoundException;
import com.digitaldart.guardian.area.shared.domain.exceptions.ValidationException;
import com.digitaldart.guardian.area.shared.domain.model.valueobjects.GuidValidator;
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
    public Optional<Device> handle(AssignDeviceCommand command) {
        var userId = externalIamService.fetchUsernameById(command.userId());
        if (userId.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        var device = deviceRepository.findByGuardianAreaDeviceRecordId(command.guardianAreaDeviceRecordId());
        if (device.isEmpty()) {
            throw new ResourceNotFoundException("Device not found");
        }
        if (device.get().getUserId() != null){
            throw new ValidationException("Device is already assigned");
        }
        device.get().assignDevice(command);
        deviceRepository.save(device.get());
        return deviceRepository.findByGuardianAreaDeviceRecordId(device.get().getGuardianAreaDeviceRecordId());
    }

    @Override
    public Optional<String> handle(RegisterDeviceCommand command) {
        var device = deviceRepository.findByGuardianAreaDeviceRecordId(command.guardianAreaDeviceRecordId());
        if (!GuidValidator.isValidGuid(command.guardianAreaDeviceRecordId().deviceRecordId())){
            throw new ValidationException("GuardianAreaDeviceRecordId must be guid");
        }
        if (device.isPresent()) {
            throw new ValidationException("Device is already registered");
        }
        var apiKey =  SecureApiKeyGenerator.generateToken();
        var newDevice = new Device(command, apiKey);
        deviceRepository.save(newDevice);
        return Optional.of(apiKey);
    }

    @Override
    public Optional<Device> handle(UpdateDeviceCommand command) {
        var device = deviceRepository.findByGuardianAreaDeviceRecordId(command.guardianAreaDeviceRecordId());
        if (device.isEmpty()) {
            throw new ValidationException("Device not found");
        }
        device.get().updateDevice(command);
        deviceRepository.save(device.get());
        return device;
    }
}
