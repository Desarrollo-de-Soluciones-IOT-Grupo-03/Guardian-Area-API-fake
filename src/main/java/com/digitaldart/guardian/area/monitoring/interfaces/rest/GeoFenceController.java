package com.digitaldart.guardian.area.monitoring.interfaces.rest;

import com.digitaldart.guardian.area.monitoring.domain.services.GeoFenceCommandService;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.CreateGeoFenceResource;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.GeoFenceResource;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.transform.CreateGeoFenceCommandFromResourceAssembler;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.transform.GeoFenceResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/geo-fences", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "GeoFences", description = "GeoFences Management Endpoints")
public class GeoFenceController {
    private final GeoFenceCommandService geoFenceCommandService;

    public GeoFenceController(GeoFenceCommandService geoFenceCommandService) {
        this.geoFenceCommandService = geoFenceCommandService;
    }

    @PostMapping
    public ResponseEntity<GeoFenceResource> createGeoFence(@RequestBody CreateGeoFenceResource createGeoFenceResource){
        var createGeoFenceCommand = CreateGeoFenceCommandFromResourceAssembler.toCommandFromResource(createGeoFenceResource);
        var geoFence = geoFenceCommandService.handle(createGeoFenceCommand);
        if (geoFence.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var geoFenceResource = GeoFenceResourceFromEntityAssembler.toResourceFromEntity(geoFence.get());
        return new ResponseEntity<>(geoFenceResource, HttpStatus.CREATED);
    }
}
