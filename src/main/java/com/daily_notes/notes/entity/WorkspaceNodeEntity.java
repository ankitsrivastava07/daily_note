package com.daily_notes.notes.entity;

import com.daily_notes.notes.enums.WorkspaceNodeType;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Getter
@Setter
@DynamoDbBean
public class WorkspaceNodeEntity extends BaseEntity {

    private String workspaceId;
    private String parentId;
    private String name;
    private String referenceId;
    private WorkspaceNodeType nodeType;

}
