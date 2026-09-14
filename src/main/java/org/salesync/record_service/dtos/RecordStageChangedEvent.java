package org.salesync.record_service.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * MiSC-Cloud event-bus pointcut: published in addition to (never instead of) the
 * existing "record" notification event, so any customization microservice can react to
 * a record's stage changing without record needing to know it exists. Carries structured
 * ids (rather than the free-text content of the "record" event) so a consumer can match
 * on typeId/stageId without an extra HTTP call back to type-service.
 */
@Builder
@Data
public class RecordStageChangedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID recordId;
    private String recordName;
    private String companyName;
    private UUID typeId;
    private UUID stageId;
    private String userId;
    private String userName;
    private Date changedAt;
}
