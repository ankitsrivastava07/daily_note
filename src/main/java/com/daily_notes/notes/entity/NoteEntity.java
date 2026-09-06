package com.daily_notes.notes.entity;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@DynamoDbBean
public class NoteEntity {

    private String userId;
    private String noteKey;

    private Instant createdAt;
    private String id;
    private String title;
    private String content;
    private List<String> attachments = new ArrayList<>();
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
    private List<String> todoIds = new ArrayList<>();
    private String priority;
    private String statusId;
    private String projectIdBandId;
    private String visibilityId;
    private String assigneeOwnerId;
    private String location;
    private String version;
    private String dueDateTime;
    private String estTime;
    private String remainderAlterId;
    private String repeatNoteAutomaticallyId;
    private String categoryId;
    private String keyTakeAwaysHighLights;
    private String description;
    private List<String> checkList = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    private String referenceURL;
    private String subCategoryId;

    @DynamoDbPartitionKey
    public String getUserId() {
        return userId;
    }

    @DynamoDbSortKey
    public String getNoteKey() {
        return noteKey;
    }
}