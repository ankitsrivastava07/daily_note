package com.daily_notes.notes.entity;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.Instant;

@Getter
@Setter
@DynamoDbBean
public class ShortNoteEntity extends BaseEntity {

    private String userId;
    private String name;
    private String content;
    private String title;
    private String categoryId;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("user_id")
    @DynamoDbSecondaryPartitionKey(indexNames = "LatestNotesIndex")
    public String getUserId() {
        return userId;
    }

    @Override
    @DynamoDbSortKey
    @DynamoDbAttribute("id")
    public String getId() {
        return super.getId();
    }

    @Override
    @DynamoDbSecondarySortKey(indexNames = "LatestNotesIndex")
    @DynamoDbAttribute("createdAt")
    public Instant getCreatedAt() {
        return super.getCreatedAt();
    }

    @DynamoDbAttribute("categoryId")
    public String getCategoryId() {
        return categoryId;
    }
}