package com.digitaldart.guardian.area.monitoring.domain.services;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.Activity;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.CreateActivityCommand;

import java.util.Optional;

public interface ActivityCommandService {
    Optional<Activity> handle(CreateActivityCommand command);
}
