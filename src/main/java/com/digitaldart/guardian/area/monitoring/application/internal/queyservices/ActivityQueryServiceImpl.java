package com.digitaldart.guardian.area.monitoring.application.internal.queyservices;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.Activity;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllActivitiesByGuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllActivitiesByGuardianAreaDeviceRecordIdAndActivityType;
import com.digitaldart.guardian.area.monitoring.domain.services.ActivityQueryService;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.ActivityRepository;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import com.digitaldart.guardian.area.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityQueryServiceImpl implements ActivityQueryService {

    private final ActivityRepository activityRepository;
    private final DeviceRepository deviceRepository;

    public ActivityQueryServiceImpl(ActivityRepository activityRepository, DeviceRepository deviceRepository) {
        this.activityRepository = activityRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Activity> handle(GetAllActivitiesByGuardianAreaDeviceRecordId query) {
        var device = deviceRepository.findByGuardianAreaDeviceRecordId(query.guardianAreaDeviceRecordId());
        if (device.isEmpty()) {
            throw new ResourceNotFoundException("Device not found");
        }
        return activityRepository.findAllByGuardianAreaDeviceRecordId(device.get().getGuardianAreaDeviceRecordId());
    }

    @Override
    public List<Activity> handle(GetAllActivitiesByGuardianAreaDeviceRecordIdAndActivityType query) {
        var device = deviceRepository.findByGuardianAreaDeviceRecordId(query.guardianAreaDeviceRecordId());
        if (device.isEmpty()) {
            throw new ResourceNotFoundException("Device not found");
        }
        var guardianAreaDeviceRecordId = device.get().getGuardianAreaDeviceRecordId();
        var activityType = query.activityType();
        return activityRepository.findAllByGuardianAreaDeviceRecordIdAndActivityType(guardianAreaDeviceRecordId, activityType);
    }
}
