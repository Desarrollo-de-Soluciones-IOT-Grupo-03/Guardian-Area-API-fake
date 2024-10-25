package com.digitaldart.guardian.area.monitoring.application.internal.queyservices;

import com.digitaldart.guardian.area.monitoring.application.internal.outboundservices.acl.ExternalIamService;
import com.digitaldart.guardian.area.monitoring.domain.model.aggregates.Device;
import com.digitaldart.guardian.area.monitoring.domain.model.queries.GetAllDevicesByUsername;
import com.digitaldart.guardian.area.monitoring.domain.services.DeviceQueryService;
import com.digitaldart.guardian.area.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import com.digitaldart.guardian.area.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceQueryServiceImpl implements DeviceQueryService {

    private final DeviceRepository deviceRepository;
    private final ExternalIamService externalIamService;

    public DeviceQueryServiceImpl(DeviceRepository deviceRepository, ExternalIamService externalIamService) {
        this.deviceRepository = deviceRepository;
        this.externalIamService = externalIamService;
    }

    @Override
    public List<Device> handle(GetAllDevicesByUsername query) {
        var userId = externalIamService.fetchUserIdByUsername(query.username());
        if (userId.isEmpty()) {
            throw new ResourceNotFoundException("Username not found");
        }
        return deviceRepository.findAllByUserId(userId.get());
    }
}
