package com.digitaldart.guardian.area.monitoring.interfaces.rest.resource;

import java.time.LocalDate;
import java.util.Date;

public record HealthMeasureDailyAverageResource(Object date, Double avgBpm, Double avgSpo2) {
}
