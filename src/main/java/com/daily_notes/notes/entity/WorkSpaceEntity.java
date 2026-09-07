package com.daily_notes.notes.entity;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Getter
@Setter
@DynamoDbBean
public class WorkSpaceEntity extends BaseEntity {

    private String name;
    private String type;
    private String userId;

    @DynamoDbPartitionKey
    public String getUserId() {
        return userId;
    }
}
