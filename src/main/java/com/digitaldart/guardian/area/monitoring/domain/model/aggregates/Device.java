package com.digitaldart.guardian.area.monitoring.domain.model.aggregates;

import com.digitaldart.guardian.area.monitoring.domain.model.commands.CreateDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.DeviceCareModes;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.DeviceStatuses;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.UserId;
import com.digitaldart.guardian.area.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Device extends AuditableAbstractAggregateRoot<Device> {

    @Getter
    private String nickname;

    @Getter
    private String bearer;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DeviceStatuses deviceStatuses;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "care_mode")
    private DeviceCareModes deviceCareModes;

    @Embedded
    private UserId userId;

    @Getter
    @Embedded
    @Column(name = "guardian_area_device_id")
    private final GuardianAreaDeviceRecordId guardianAreaDeviceRecordId;

    public Device() {
        this.userId = new UserId();
        this.deviceStatuses = DeviceStatuses.DISCONNECTED;
        this.deviceCareModes = DeviceCareModes.INFANT;
        this.guardianAreaDeviceRecordId = new GuardianAreaDeviceRecordId();
    }

    public Device(UserId userId) {
        this();
        this.userId = userId;
    }

    public Device(UserId userId, CreateDeviceCommand command){
        this(userId);
        this.nickname = command.nickname();
        this.bearer = command.bearer();
        this.deviceCareModes = command.careMode();
        this.deviceStatuses = command.status();
    }

    public String getDeviceRecordId() {
        return this.guardianAreaDeviceRecordId.deviceRecordId();
    }

    public Long getUserId() {
        return this.userId.userId();
    }
}
