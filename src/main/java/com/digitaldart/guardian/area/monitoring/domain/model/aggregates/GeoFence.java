package com.digitaldart.guardian.area.monitoring.domain.model.aggregates;

import com.digitaldart.guardian.area.monitoring.domain.model.commands.CreateGeoFenceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.commands.UpdateGeoFenceCommand;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.Coordinate;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GeoFenceStatuses;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.shared.domain.exceptions.ValidationException;
import com.digitaldart.guardian.area.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import com.digitaldart.guardian.area.shared.domain.model.valueobjects.Pair;

import java.util.List;

@Entity
public class GeoFence extends AuditableAbstractAggregateRoot<GeoFence> {

    @Getter
    private String name;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private GeoFenceStatuses geoFenceStatus;

    @Getter
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "geofence_coordinates", joinColumns = @JoinColumn(name = "geofence_id"))
    private List<Coordinate> coordinates;

    @Getter
    @Embedded
    private GuardianAreaDeviceRecordId guardianAreaDeviceRecordId;

    private static final int MAX_COORDINATES = 4;

    public GeoFence(){

    }

    public GeoFence(CreateGeoFenceCommand command) {
        this.guardianAreaDeviceRecordId = command.guardianAreaDeviceRecordId();
        this.name = command.name();
        this.geoFenceStatus = command.geoFenceStatus();
        this.coordinates = command.coordinates();
        this.validateCoordinates();
    }

    private void validateCoordinates() {
        if (coordinates.size() > MAX_COORDINATES) {
            throw new ValidationException("Geofence can have only " + MAX_COORDINATES + " coordinates.");
        }
    }

    public static Coordinate toCoordinateFromLatitudeAndLongitudePair(Pair<Float, Float> coordinate) {
        return new Coordinate(coordinate.latitude(), coordinate.longitude());
    }

    public static Pair<Float, Float> toLatitudeAndLongitudePairFromCoordinate(Coordinate coordinate) {
        return new Pair<>(coordinate.latitude(), coordinate.longitude());
    }

    public void updateGeofence(UpdateGeoFenceCommand command){
        this.name = command.name();
        this.geoFenceStatus = command.geoFenceStatus();
        this.coordinates.clear();
        this.coordinates.addAll(command.coordinates());
        validateCoordinates();
    }

    public String getDeviceRecordId() {
        return this.guardianAreaDeviceRecordId.deviceRecordId();
    }

}
