package com.digitaldart.guardian.area.monitoring.domain.model.aggregates;

import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.BeatsPerMinute;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.SaturationOfPeripheralOxygen;
import com.digitaldart.guardian.area.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class HealthMeasure extends AuditableAbstractAggregateRoot<HealthMeasure> {

    @Getter
    @Embedded
    @Column(name = "bpm")
    private BeatsPerMinute bpm;

    @Getter
    @Embedded
    @Column(name = "spo2")
    private SaturationOfPeripheralOxygen spo2;

    @Getter
    @Embedded
    @Column(name = "guardian_area_device_id")
    private GuardianAreaDeviceRecordId guardianAreaDeviceRecordId;

    public HealthMeasure() {}
    public HealthMeasure(BeatsPerMinute bpm, SaturationOfPeripheralOxygen spo2, GuardianAreaDeviceRecordId guardianAreaDeviceRecordId) {
        this.bpm = bpm;
        this.spo2 = spo2;
        this.guardianAreaDeviceRecordId = guardianAreaDeviceRecordId;
    }
}
