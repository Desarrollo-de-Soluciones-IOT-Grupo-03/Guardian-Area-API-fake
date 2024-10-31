package com.digitaldart.guardian.area.monitoring.interfaces.rest;

import com.digitaldart.guardian.area.monitoring.domain.model.commands.RegisterDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.UpdateDeviceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetDeviceByGuardianAreaDeviceRecordIdQuery;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.domain.services.DeviceCommandService;
import com.digitaldart.guardian.area.monitoring.domain.services.DeviceQueryService;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.*;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.transform.AssignDeviceCommandFromResourceAssembler;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.transform.DeviceResourceFromEntityAssembler;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.transform.RegisterDeviceCommandFromResourceAssembler;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.transform.UpdateDeviceCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/devices", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Devices", description = "Devices Management Endpoints")
public class DevicesController {
    private final DeviceCommandService deviceCommandService;
    private final DeviceQueryService deviceQueryService;

    public DevicesController(DeviceCommandService deviceCommandService, DeviceQueryService deviceQueryService) {
        this.deviceCommandService = deviceCommandService;
        this.deviceQueryService = deviceQueryService;
    }

    @PostMapping("/assign")
    public ResponseEntity<DeviceResource> assignDevice(@RequestBody AssignDeviceResource createDeviceResource){
        var assignDeviceCommand = AssignDeviceCommandFromResourceAssembler.toCommandFromResource(createDeviceResource);
        var device = deviceCommandService.handle(assignDeviceCommand);
        if (device.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device.get());
        return new ResponseEntity<>(deviceResource, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiKeyResource> registerDevice(@RequestBody RegisterDeviceResource registerDeviceResource) {
        var registerDeviceCommand = RegisterDeviceCommandFromResourceAssembler.toCommandFromResource(registerDeviceResource);
        var apiKey = deviceCommandService.handle(registerDeviceCommand);
        if (apiKey.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var apiKeyResource = new ApiKeyResource(apiKey.get());
        return new ResponseEntity<>(apiKeyResource, HttpStatus.CREATED);
    }

    @GetMapping("/{deviceRecordId}")
    public ResponseEntity<DeviceResource> getDeviceByDeviceRecordId(@PathVariable String deviceRecordId) {
        var guardianAreaDeviceRecordId = new GuardianAreaDeviceRecordId(deviceRecordId);
        var getDeviceByGuardianAreaDeviceRecordIdQuery = new GetDeviceByGuardianAreaDeviceRecordIdQuery(guardianAreaDeviceRecordId);
        var device = deviceQueryService.handle(getDeviceByGuardianAreaDeviceRecordIdQuery);
        if (device.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device.get());
        return ResponseEntity.ok(deviceResource);
    }

    @PutMapping("/{deviceRecordId}")
    public ResponseEntity<DeviceResource> updateDeviceByDeviceRecordId(@PathVariable String deviceRecordId, @RequestBody UpdateDeviceResource updateDeviceResource) {
        var updateDeviceCommand = UpdateDeviceCommandFromResourceAssembler.toCommandFromResource(deviceRecordId, updateDeviceResource);
        var device = deviceCommandService.handle(updateDeviceCommand);
        if (device.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device.get());
        return ResponseEntity.ok(deviceResource);
    }

}
