package com.digitaldart.guardian.area.monitoring.interfaces.rest.transform;

import com.digitaldart.guardian.area.monitoring.domain.model.commands.UpdateGeoFenceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.Coordinate;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GeoFenceStatuses;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.UpdateGeoFenceResource;

public class UpdateGeoFenceCommandFromResourceAssembler {
    public static UpdateGeoFenceCommand toCommandFromResource(Long id, UpdateGeoFenceResource resource) {
        return new UpdateGeoFenceCommand(
                id,
                resource.name(),
                GeoFenceStatuses.valueOf(resource.geoFenceStatus()),
                resource.coordinates().stream().map((coordinate) -> new Coordinate(coordinate.latitude(), coordinate.longitude())).toList()
        );
    }
}
