package com.daily_notes.notes.entity;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.time.Instant;

@Getter
@Setter
@DynamoDbBean
public class BaseEntity {

    private String id;
    private Instant createdAt;
    private Instant updatedAt;
}