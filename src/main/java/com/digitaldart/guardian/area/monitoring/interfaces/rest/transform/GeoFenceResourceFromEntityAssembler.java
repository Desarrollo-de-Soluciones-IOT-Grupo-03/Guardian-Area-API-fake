package com.digitaldart.guardian.area.monitoring.interfaces.rest.transform;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.GeoFence;
import com.digitaldart.guardian.area.monitoring.interfaces.rest.resource.GeoFenceResource;

public class GeoFenceResourceFromEntityAssembler {
    public static GeoFenceResource toResourceFromEntity(GeoFence geoFence){
        return new GeoFenceResource(
                geoFence.getId(),
                geoFence.getName(),
                geoFence.getGeoFenceStatus().toString(),
                geoFence.getCoordinates().stream().map(GeoFence::toLatitudeAndLongitudePairFromCoordinate).toList(),
                geoFence.getDeviceRecordId()
        );
    }
}
