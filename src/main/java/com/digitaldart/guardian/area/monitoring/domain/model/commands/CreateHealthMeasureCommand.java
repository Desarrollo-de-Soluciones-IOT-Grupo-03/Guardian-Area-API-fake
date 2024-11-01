package com.digitaldart.guardian.area.monitoring.domain.model.commands;

import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.ApiKey;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.BeatsPerMinute;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.SaturationOfPeripheralOxygen;

public record CreateHealthMeasureCommand(BeatsPerMinute bpm, SaturationOfPeripheralOxygen spo2, GuardianAreaDeviceRecordId guardianAreaDeviceRecordId, ApiKey apiKey) {
}
