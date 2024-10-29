package com.digitaldart.guardian.area.monitoring.interfaces.rest;

import com.digitaldart.guardian.area.monitoring.domain.services.DeviceCommandService;
import com.digitaldart.guardian.area.monitoring.domain.services.DeviceQueryService;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.CreateDeviceResource;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.DeviceResource;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.transform.CreateDeviceCommandFromResourceAssembler;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.transform.DeviceResourceFromEntityAssembler;
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

    public DevicesController(DeviceCommandService deviceCommandService) {
        this.deviceCommandService = deviceCommandService;
    }

    @PostMapping
    public ResponseEntity<DeviceResource> createDevice(@RequestBody CreateDeviceResource createDeviceResource){
        var createDeviceCommand = CreateDeviceCommandFromResourceAssembler.toCommandFromResource(createDeviceResource);
        var device = deviceCommandService.handle(createDeviceCommand);
        if (device.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device.get());
        return new ResponseEntity<>(deviceResource, HttpStatus.CREATED);
    }

}
