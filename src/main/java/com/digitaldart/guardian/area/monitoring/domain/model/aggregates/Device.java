package com.digitaldart.guardian.area.monitoring.domain.model.aggregates;

import com.digitaldart.guardian.area.monitoring.domain.model.commands.AssignDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.RegisterDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.UpdateDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.*;
import com.digitaldart.guardian.area.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Device extends AuditableAbstractAggregateRoot<Device> {

    @Getter
    private String deviceNickname;

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

    @Setter
    @Embedded
    private UserId userId;

    @Getter
    @Embedded
    @Column(name = "guardian_area_device_id")
    private GuardianAreaDeviceRecordId guardianAreaDeviceRecordId;

    @Getter
    @Embedded
    private ApiKey apiKey;

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

    public Device(AssignDeviceCommand command){
        this(new UserId(command.userId()));
        this.guardianAreaDeviceRecordId = command.guardianAreaDeviceRecordId();
        this.deviceNickname = "-";
        this.bearer = "-";
        this.deviceCareModes = DeviceCareModes.INFANT;
        this.deviceStatuses = DeviceStatuses.CONNECTED;
    }

    public Device(RegisterDeviceCommand command, String apiKey) {
        this.userId = null;
        this.deviceNickname = null;
        this.bearer = null;
        this.deviceStatuses = DeviceStatuses.DISCONNECTED;
        this.deviceCareModes = DeviceCareModes.INFANT;
        this.guardianAreaDeviceRecordId = command.guardianAreaDeviceRecordId();
        this.apiKey = new ApiKey(apiKey);
    }

    public void updateDevice(UpdateDeviceCommand command){
        this.deviceNickname = command.deviceNickname();
        this.deviceCareModes = command.deviceCareModes();
        this.bearer = command.bearer();
        this.deviceStatuses = command.deviceStatuses();
    }

    public String getDeviceRecordId() {
        return this.guardianAreaDeviceRecordId.deviceRecordId();
    }

    public Long getUserId() {
        return this.userId.userId();
    }

}
