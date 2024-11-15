package com.digitaldart.guardian.area.monitoring.domain.services;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.HealthMeasure;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllHealthMeasuresByDeviceRecordIdQuery;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetHealthMeasuresDailyAverageFromCurrentMonthByGuardianAreaDeviceRecordIdQuery;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.HealthMeasureDailyAverage;

import java.util.List;

public interface HealthMeasureQueryService {
    List<HealthMeasure> handle(GetAllHealthMeasuresByDeviceRecordIdQuery query);
    List<HealthMeasureDailyAverage> handle(GetHealthMeasuresDailyAverageFromCurrentMonthByGuardianAreaDeviceRecordIdQuery query);
}
