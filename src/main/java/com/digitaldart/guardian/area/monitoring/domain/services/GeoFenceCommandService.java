package com.digitaldart.guardian.area.monitoring.domain.services;
import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.GeoFence;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.CreateGeoFenceCommand;

import java.util.Optional;

public interface GeoFenceCommandService {
    Optional<GeoFence> handle(CreateGeoFenceCommand command);
}
