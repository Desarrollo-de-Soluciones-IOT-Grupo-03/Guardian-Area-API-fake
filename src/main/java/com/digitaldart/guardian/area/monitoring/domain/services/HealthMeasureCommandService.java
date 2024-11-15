package com.digitaldart.guardian.area.monitoring.domain.services;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.HealthMeasure;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.CreateHealthMeasureCommand;

import java.util.Optional;

public interface HealthMeasureCommandService {
    Optional<HealthMeasure> handle(CreateHealthMeasureCommand command);
}
