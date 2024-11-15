package com.digitaldart.guardian.area.monitoring.application.internal.queyservices;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.GeoFence;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllGeoFencesByGuardianAreaDeviceRecordIdQuery;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetDeviceByGuardianAreaDeviceRecordIdQuery;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetGeoFenceByIdQuery;
import com.digitaldart.guardian.area.monitoring.domain.services.GeoFenceQueryService;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.GeoFenceRepository;
import com.digitaldart.guardian.area.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeoFenceQueryServiceImpl implements GeoFenceQueryService {

    private final GeoFenceRepository geoFenceRepository;
    private final DeviceRepository deviceRepository;

    public GeoFenceQueryServiceImpl(GeoFenceRepository geoFenceRepository, DeviceRepository deviceRepository) {
        this.geoFenceRepository = geoFenceRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<GeoFence> handle(GetAllGeoFencesByGuardianAreaDeviceRecordIdQuery query) {
        var device = deviceRepository.findByGuardianAreaDeviceRecordId(query.guardianAreaDeviceRecordId());
        if (device.isEmpty()){
            throw new ResourceNotFoundException("Device not found");
        }
        return geoFenceRepository.findAllByGuardianAreaDeviceRecordId(device.get().getGuardianAreaDeviceRecordId());
    }

    @Override
    public Optional<GeoFence> handle(GetGeoFenceByIdQuery query) {
        var geoFence = geoFenceRepository.findById(query.geoFenceId());
        if (geoFence.isEmpty()){
            throw new ResourceNotFoundException("GeoFence not found");
        }
        return geoFence;
    }
}
