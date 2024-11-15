package com.digitaldart.guardian.area.monitoring.application.internal.commandservices;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.Activity;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.CreateActivityCommand;
import com.digitaldart.guardian.area.monitoring.domain.services.ActivityCommandService;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.ActivityRepository;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.ActivityRepository;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import com.digitaldart.guardian.area.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityCommandServiceImpl implements ActivityCommandService {

    private final ActivityRepository activityRepository;
    private final DeviceRepository deviceRepository;

    public ActivityCommandServiceImpl(ActivityRepository activityRepository, DeviceRepository deviceRepository) {
        this.activityRepository = activityRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Optional<Activity> handle(CreateActivityCommand command) {
        var device = deviceRepository.findByGuardianAreaDeviceRecordId(command.guardianAreaDeviceRecordId());
        if (device.isEmpty()) {
            throw new ResourceNotFoundException("Device not found");
        }
        var activity = new Activity(
                command.guardianAreaDeviceRecordId(),
                command.activityEventName(),
                command.activityType()
        );
        var activityCreated = activityRepository.save(activity);
        return Optional.of(activityCreated);
    }
}
