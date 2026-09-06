package com.daily_notes.notes.entity;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@Getter
@Setter
@DynamoDbBean
public class ShortNoteEntity extends BaseEntity {

    private String id;
    private String userId;
    private String name;
    private String content;
    private String title;
    private String categoryId;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("user_id")
    @DynamoDbSecondaryPartitionKey(indexNames = "CategoryIndex") // GSI Partition Key
    public String getUserId() {
        return userId;
    }
    @DynamoDbAttribute("id")
    @DynamoDbSortKey
    public String getId() {
        return id;
    }

    @DynamoDbSecondarySortKey(indexNames = "CategoryIndex") // GSI Sort Key
    public String getCategoryId() {
        return categoryId;
    }
}