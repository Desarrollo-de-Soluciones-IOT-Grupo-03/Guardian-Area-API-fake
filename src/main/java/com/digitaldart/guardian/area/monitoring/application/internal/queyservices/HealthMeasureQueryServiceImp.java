package com.digitaldart.guardian.area.monitoring.application.internal.queyservices;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.HealthMeasure;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllHealthMeasuresByDeviceRecordIdQuery;
import com.digitaldart.guardian.area.monitoring.domain.services.HealthMeasureQueryService;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.HealthMeasureRepository;
import com.digitaldart.guardian.area.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthMeasureQueryServiceImp implements HealthMeasureQueryService {

    private final HealthMeasureRepository healthMeasureRepository;
    private final DeviceRepository deviceRepository;

    public HealthMeasureQueryServiceImp(HealthMeasureRepository healthMeasureRepository, DeviceRepository deviceRepository) {
        this.healthMeasureRepository = healthMeasureRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<HealthMeasure> handle(GetAllHealthMeasuresByDeviceRecordIdQuery query) {
        var existsDeviceByGuardianAreaDeviceRecordId = deviceRepository.existsByGuardianAreaDeviceRecordId(query.guardianAreaDeviceRecordId());
        if (!existsDeviceByGuardianAreaDeviceRecordId) {
            throw new ResourceNotFoundException("Device does not exist");
        }
        return healthMeasureRepository.findAllByGuardianAreaDeviceRecordId(query.guardianAreaDeviceRecordId());
    }
}
