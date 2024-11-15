package com.digitaldart.guardian.area.monitoring.interfaces.rest.resource;

import java.util.Date;

public record ActivityResource(String guardianAreaDeviceRecordId, String activityName, String activityType, Date dateAndTime) {
}
