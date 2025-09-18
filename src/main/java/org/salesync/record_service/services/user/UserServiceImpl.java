package org.salesync.record_service.services.user;

import java.util.UUID;

import org.salesync.record_service.dtos.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl implements UserService {

    private final RestTemplateBuilder restTemplateBuilder;
    
    @Value("${userinfo.url}")
    private String userInfoUrl;

    @Override
    public UserDto getUserInfo(String userId) {
        return restTemplateBuilder.build().getForObject(userInfoUrl + "/" + userId, UserDto.class);
    }
}