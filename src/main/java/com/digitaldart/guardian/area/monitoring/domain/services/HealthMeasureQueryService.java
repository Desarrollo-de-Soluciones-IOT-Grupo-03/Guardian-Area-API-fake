package com.digitaldart.guardian.area.monitoring.domain.services;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.HealthMeasure;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllHealthMeasuresByDeviceRecordIdQuery;

import java.util.List;

public interface HealthMeasureQueryService {
    List<HealthMeasure> handle(GetAllHealthMeasuresByDeviceRecordIdQuery query);
}
