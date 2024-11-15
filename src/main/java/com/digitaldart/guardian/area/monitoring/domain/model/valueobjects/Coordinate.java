package com.digitaldart.guardian.area.monitoring.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Coordinate(Float latitude, Float longitude) {
}
