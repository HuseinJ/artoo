package com.hjusic.artoo.model;

import java.math.BigDecimal;

public record EmailNotificationMessage(
    String eventId,
    BigDecimal occurredOn,
    Notification notification
){}