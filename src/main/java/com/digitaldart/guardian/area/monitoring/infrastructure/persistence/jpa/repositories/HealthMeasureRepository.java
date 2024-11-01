package com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories;

import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.HealthMeasure;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.GuardianAreaDeviceRecordId;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.HealthMeasureDailyAverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthMeasureRepository extends JpaRepository<HealthMeasure, Long> {
    List<HealthMeasure> findAllByGuardianAreaDeviceRecordId(GuardianAreaDeviceRecordId guardianAreaDeviceRecordId);
    @Query("SELECT " +
            "NEW com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.HealthMeasureDailyAverage( " +
            "    FUNCTION('DATE', h.createdAt), " +
            "    AVG(h.bpm.bpm), " +
            "    AVG(h.spo2.spo2) " +
            ") " +
            "FROM HealthMeasure h " +
            "WHERE FUNCTION('MONTH', h.createdAt) = :month " +
            "AND FUNCTION('YEAR', h.createdAt) = :year " +
            "AND h.guardianAreaDeviceRecordId = :guardianAreaDeviceRecordId " +
            "GROUP BY FUNCTION('DATE', h.createdAt) " +
            "ORDER BY FUNCTION('DATE', h.createdAt) ASC")
    List<HealthMeasureDailyAverage> findDailyAveragesForCurrentMonthAndGuardian(
            @Param("month") int month,
            @Param("year") int year,
            @Param("guardianAreaDeviceRecordId") GuardianAreaDeviceRecordId guardianAreaDeviceRecordId);

}
