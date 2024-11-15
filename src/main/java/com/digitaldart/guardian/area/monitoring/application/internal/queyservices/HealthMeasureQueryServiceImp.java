package com.digitaldart.guardian.area.monitoring.application.internal.queyservices;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.HealthMeasure;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllHealthMeasuresByDeviceRecordIdQuery;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetHealthMeasuresDailyAverageFromCurrentMonthByGuardianAreaDeviceRecordIdQuery;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.HealthMeasureDailyAverage;
import com.digitaldart.guardian.area.monitoring.domain.services.HealthMeasureQueryService;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.HealthMeasureRepository;
import com.digitaldart.guardian.area.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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
        var device = deviceRepository.findByGuardianAreaDeviceRecordId(query.guardianAreaDeviceRecordId());
        if (device.isEmpty()) {
            throw new ResourceNotFoundException("Device does not exist");
        }
        return healthMeasureRepository.findAllByGuardianAreaDeviceRecordId(query.guardianAreaDeviceRecordId());
    }

    @Override
    public List<HealthMeasureDailyAverage> handle(GetHealthMeasuresDailyAverageFromCurrentMonthByGuardianAreaDeviceRecordIdQuery query) {
        var device = deviceRepository.findByGuardianAreaDeviceRecordId(query.guardianAreaDeviceRecordId());
        if (device.isEmpty()) {
            throw new ResourceNotFoundException("Device does not exist");
        }
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        return healthMeasureRepository.findDailyAveragesForCurrentMonthAndGuardian(currentMonth, currentYear, device.get().getGuardianAreaDeviceRecordId());
    }
}
