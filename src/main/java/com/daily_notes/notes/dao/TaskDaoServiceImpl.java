package com.daily_notes.notes.dao;

import com.daily_notes.notes.entity.TaskEntity;
import com.daily_notes.notes.util.IdGenerator;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.*;

@Repository
public class TaskDaoServiceImpl implements TaskDaoService {

    private final DynamoDbTable<TaskEntity> table;

    public TaskDaoServiceImpl(DynamoDbEnhancedClient client) {
        this.table = client.table("task", TableSchema.fromBean(TaskEntity.class));
    }

    @Override
    public void createTask(TaskEntity taskEntity) {
        taskEntity.setId(IdGenerator.generateId());
        table.putItem(taskEntity);
    }

    @Override
    public Page<TaskEntity> getAllTasksByUserId(
            String userId,
            Integer limit,
            String lastId,
            String search) {

        QueryEnhancedRequest.Builder query =
                QueryEnhancedRequest.builder()
                        .queryConditional(
                                QueryConditional.keyEqualTo(
                                        Key.builder()
                                                .partitionValue(userId)
                                                .build()
                                )
                        )
                        .scanIndexForward(false) // DESC
                        .limit(limit);

        // ==========================================
        // PAGINATION
        // ==========================================

        if (lastId != null && !lastId.isBlank()) {

            Map<String, AttributeValue> startKey =
                    new HashMap<>();

            startKey.put(
                    "userId",
                    AttributeValue.builder()
                            .s(userId)
                            .build()
            );

            startKey.put(
                    "id",
                    AttributeValue.builder()
                            .s(lastId)
                            .build()
            );

            query.exclusiveStartKey(startKey);
        }


        // ==========================================
        // SEARCH
        // ==========================================

        if (search != null && !search.isBlank()) {

            Map<String, String> expressionNames =
                    new HashMap<>();

            expressionNames.put("#name", "name");
            expressionNames.put("#content", "content");
            expressionNames.put("#priority", "priority");
            expressionNames.put("#status", "status");


            Map<String, AttributeValue> expressionValues =
                    new HashMap<>();

            // Original search for name/content
            expressionValues.put(
                    ":search",
                    AttributeValue.builder()
                            .s(search.trim())
                            .build()
            );

            // Uppercase for priority/status
            expressionValues.put(
                    ":searchUpper",
                    AttributeValue.builder()
                            .s(search.trim().toUpperCase())
                            .build()
            );

            Expression filterExpression =
                    Expression.builder()
                            .expression(
                                    "contains(#name, :search) " +
                                            "OR contains(#content, :search) " +
                                            "OR #priority = :searchUpper " +
                                            "OR #status = :searchUpper"
                            )
                            .expressionNames(expressionNames)
                            .expressionValues(expressionValues)
                            .build();


            query.filterExpression(filterExpression);
        }

        return table
                .query(query.build())
                .iterator()
                .next();
    }
}
