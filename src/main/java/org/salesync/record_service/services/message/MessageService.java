package org.salesync.record_service.services.message;

import org.salesync.record_service.entities.Record;

import java.util.UUID;

public interface MessageService {
    void sendMessage(String userId, Record record, String messageTemplate);
}
