package org.salesync.record_service.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Builder
@Data
public class RecordChangedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID recordId;
    private String recordName;
    private String companyName;
    private UUID typeId;
    private Map<String, String> properties;
    private Date changedAt;
}
