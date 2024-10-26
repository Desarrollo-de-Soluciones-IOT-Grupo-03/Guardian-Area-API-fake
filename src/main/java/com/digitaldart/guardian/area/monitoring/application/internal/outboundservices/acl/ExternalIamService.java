package com.digitaldart.guardian.area.monitoring.application.internal.outboundservices.acl;

import com.digitaldart.guardian.area.iam.interfaces.acl.IamContextFacade;
import com.digitaldart.guardian.area.monitoring.domain.model.valueobjects.UserId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalIamService {

    private final IamContextFacade iamContextFacade;

    public ExternalIamService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Optional<UserId> fetchUserIdByUsername(String username){
        var userId = iamContextFacade.fetchUserIdByUsername(username);
        if (userId == 0L) return Optional.empty();
        return Optional.of(new UserId(userId));
    }

    public Optional<UserId> createUser(String username, String password, String email, String firstName, String lastName) {
        var userId = iamContextFacade.createUser(username, email, firstName, lastName,password);
        if (userId == 0L) return Optional.empty();
        return Optional.of(new UserId(userId));
    }
}
