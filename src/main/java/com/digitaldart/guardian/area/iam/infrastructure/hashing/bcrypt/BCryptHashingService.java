package com.digitaldart.guardian.area.iam.infrastructure.hashing.bcrypt;

import com.digitaldart.guardian.area.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
