package com.digitaldart.guardian.area.monitoring.application.internal.commandservices;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.HealthMeasure;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.CreateHealthMeasureCommand;
import com.digitaldart.guardian.area.monitoring.domain.services.HealthMeasureCommandService;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.HealthMeasureRepository;
import com.digitaldart.guardian.area.shared.domain.exceptions.ResourceNotFoundException;
import com.digitaldart.guardian.area.shared.domain.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HealthMeasureCommandServiceImp implements HealthMeasureCommandService {

    private final HealthMeasureRepository healthMeasureRepository;
    private final DeviceRepository deviceRepository;

    public HealthMeasureCommandServiceImp(HealthMeasureRepository healthMeasureRepository, DeviceRepository deviceRepository) {
        this.healthMeasureRepository = healthMeasureRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Optional<HealthMeasure> handle(CreateHealthMeasureCommand command) {
        var device = deviceRepository.findByGuardianAreaDeviceRecordIdAndApiKey(command.guardianAreaDeviceRecordId(), command.apiKey());
        if (device.isEmpty()) {
            throw new ValidationException("Incorrect device Id or API key");
        }
        var healthMeasure = new HealthMeasure(
                command.bpm(),
                command.spo2(),
                command.guardianAreaDeviceRecordId()
        );
        var healthMeasureCreated = healthMeasureRepository.save(healthMeasure);
        return Optional.of(healthMeasureCreated);
    }
}
