package com.digitaldart.guardian.area.monitoring.interfaces.rest;

import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllActivitiesByGuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllActivitiesByGuardianAreaDeviceRecordIdAndActivityType;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.ActivityType;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.domain.services.ActivityQueryService;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.ActivityResource;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.transform.ActivityResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/devices/{deviceRecordId}/activities", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Devices")
public class DeviceActivityController {
    private final ActivityQueryService activityQueryService;

    public DeviceActivityController(ActivityQueryService activityQueryService) {
        this.activityQueryService = activityQueryService;
    }

    @GetMapping
    public ResponseEntity<List<ActivityResource>> getActivitiesFromDeviceRecordId(@PathVariable String deviceRecordId, @RequestParam(required = false) String activityType){
        var guardianAreaDeviceRecordId = new GuardianAreaDeviceRecordId(deviceRecordId);
        if (activityType != null) {
            var activityTypeEnum = ActivityType.valueOf(activityType);
            var query = new GetAllActivitiesByGuardianAreaDeviceRecordIdAndActivityType(guardianAreaDeviceRecordId, activityTypeEnum);
            var activities = activityQueryService.handle(query);
            var activitiesResource = activities.stream().map(ActivityResourceFromEntityAssembler::toResourceFromEntity).toList();
            return ResponseEntity.ok(activitiesResource);
        }
        else {
            var query = new GetAllActivitiesByGuardianAreaDeviceRecordId(guardianAreaDeviceRecordId);
            var activities = activityQueryService.handle(query);
            var activitiesResource = activities.stream().map(ActivityResourceFromEntityAssembler::toResourceFromEntity).toList();
            return ResponseEntity.ok(activitiesResource);
        }
    }

}
