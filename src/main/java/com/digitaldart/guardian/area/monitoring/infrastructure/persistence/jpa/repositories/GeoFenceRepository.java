package com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.GeoFence;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeoFenceRepository extends JpaRepository<GeoFence, Long> {
    boolean existsByNameAndGuardianAreaDeviceRecordId(String name, GuardianAreaDeviceRecordId guardianAreaDeviceRecordId);
    Optional<GeoFence> findByGuardianAreaDeviceRecordId(GuardianAreaDeviceRecordId guardianAreaDeviceRecordId);
    Optional<GeoFence> findByNameAndGuardianAreaDeviceRecordId(String name, GuardianAreaDeviceRecordId guardianAreaDeviceRecordId);
    List<GeoFence> findAllByGuardianAreaDeviceRecordId(GuardianAreaDeviceRecordId guardianAreaDeviceRecordId);
}
