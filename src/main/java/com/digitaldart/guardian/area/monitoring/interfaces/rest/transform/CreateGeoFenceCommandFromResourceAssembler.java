package com.digitaldart.guardian.area.monitoring.interfaces.rest.transform;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.GeoFence;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.CreateGeoFenceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GeoFenceStatuses;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.CreateGeoFenceResource;

public class CreateGeoFenceCommandFromResourceAssembler {
    public static CreateGeoFenceCommand toCommandFromResource(CreateGeoFenceResource resource){
        return new CreateGeoFenceCommand(
                new GuardianAreaDeviceRecordId(resource.guardianAreaDeviceRecordId()),
                resource.name(),
                GeoFenceStatuses.valueOf(resource.geoFenceStatus()),
                resource.coordinates().stream().map(GeoFence::toCoordinateFromLatitudeAndLongitudePair).toList()
        );
    }
}
