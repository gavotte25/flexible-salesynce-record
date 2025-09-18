package org.salesync.record_service.services.message;

import org.salesync.record_service.dtos.UserDto;
import org.salesync.record_service.entities.Record;
import org.salesync.record_service.services.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
public class MessageServiceImpl implements MessageService {

    @Value("${slack.webhook_url}")
    private String slackWebhookUrl;

    private final String USER_NAME_PLACEHOLDER = "<USER_NAME_PLACEHOLDER>";
    private final String RECORD_NAME_PLACEHOLDER = "<RECORD_NAME_PLACEHOLDER>";
    
    private final RestTemplateBuilder restTemplateBuilder;
    private final UserService userService;

    public void sendMessage(String content) {
        String request = "{\"text\": \"" + content + "\"}";
        restTemplateBuilder.build().postForObject(slackWebhookUrl,request,String.class);  
    }

    @Override
    public void sendMessage(String userId, Record record, String messageTemplate) {
        UserDto user = userService.getUserInfo(userId);
        if (user != null) {
            String content = messageTemplate
                .replace(USER_NAME_PLACEHOLDER, user.getUserName())
                .replace(RECORD_NAME_PLACEHOLDER, record.getName());
            sendMessage(content);
        }
    }
}
