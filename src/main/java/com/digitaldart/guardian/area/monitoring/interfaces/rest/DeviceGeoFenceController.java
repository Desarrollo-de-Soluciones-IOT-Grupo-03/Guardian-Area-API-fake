package com.digitaldart.guardian.area.monitoring.interfaces.rest;

import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllGeoFencesByGuardianAreaDeviceRecordIdQuery;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.domain.services.GeoFenceQueryService;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.GeoFenceResource;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.transform.GeoFenceResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/devices/{deviceRecordId}/geo-fences", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Devices")
public class DeviceGeoFenceController {
    private final GeoFenceQueryService geoFenceQueryService;

    public DeviceGeoFenceController(GeoFenceQueryService geoFenceQueryService) {
        this.geoFenceQueryService = geoFenceQueryService;
    }

    @GetMapping
    public ResponseEntity<List<GeoFenceResource>>getGeoFencesForDevicesWithDeviceRecordId(@PathVariable String deviceRecordId){
        var guardianAreaDeviceRecordId = new GuardianAreaDeviceRecordId(deviceRecordId);
        var getAllGeoFencesByGuardianAreaDeviceRecordIdQuery = new GetAllGeoFencesByGuardianAreaDeviceRecordIdQuery(guardianAreaDeviceRecordId);
        var geoFences = geoFenceQueryService.handle(getAllGeoFencesByGuardianAreaDeviceRecordIdQuery);
        var geoFencesResources = geoFences.stream().map(GeoFenceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(geoFencesResources);
    }
}
