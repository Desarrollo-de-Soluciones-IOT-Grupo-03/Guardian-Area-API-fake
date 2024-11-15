package com.digitaldart.guardian.area.monitoring.application.internal.commandservices;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.GeoFence;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.CreateGeoFenceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.UpdateGeoFenceCommand;
import com.digitaldart.guardian.area.monitoring.domain.services.GeoFenceCommandService;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.GeoFenceRepository;
import com.digitaldart.guardian.area.shared.domain.exceptions.ResourceNotFoundException;
import com.digitaldart.guardian.area.shared.domain.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeoFenceCommandServiceImpl implements GeoFenceCommandService {

    private final GeoFenceRepository geoFenceRepository;
    private final DeviceRepository deviceRepository;

    public GeoFenceCommandServiceImpl(GeoFenceRepository geoFenceRepository, DeviceRepository deviceRepository) {
        this.geoFenceRepository = geoFenceRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Optional<GeoFence> handle(CreateGeoFenceCommand command) {
        var existsByNameAndGuardianAreaDeviceRecordId= geoFenceRepository.existsByNameAndGuardianAreaDeviceRecordId(command.name(), command.guardianAreaDeviceRecordId());
        if (existsByNameAndGuardianAreaDeviceRecordId) {
            throw new ValidationException("GeoFence with the same name and device already exists");
        }
        var device = deviceRepository.findByGuardianAreaDeviceRecordId(command.guardianAreaDeviceRecordId());
        if (device.isEmpty()){
            throw new ResourceNotFoundException("Device not found");
        }
        var geoFence = new GeoFence(command);
        geoFenceRepository.save(geoFence);
        return geoFenceRepository.findByNameAndGuardianAreaDeviceRecordId(geoFence.getName(), geoFence.getGuardianAreaDeviceRecordId());
    }

    @Override
    public Optional<GeoFence> handle(UpdateGeoFenceCommand command) {
        var geoFence = geoFenceRepository.findById(command.geoFenceId());
        if (geoFence.isEmpty()) {
            throw new ValidationException("GeoFence not found");
        }
        geoFence.get().updateGeofence(command);
        geoFenceRepository.save(geoFence.get());
        return geoFence;
    }
}
