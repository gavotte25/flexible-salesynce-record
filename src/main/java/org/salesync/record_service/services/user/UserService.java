package org.salesync.record_service.services.user;

import java.util.UUID;

import org.salesync.record_service.dtos.UserDto;

public interface UserService {
    UserDto getUserInfo(String userId);
}
