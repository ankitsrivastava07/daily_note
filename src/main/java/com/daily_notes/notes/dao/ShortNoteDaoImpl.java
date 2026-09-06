package com.daily_notes.notes.dao;

import com.daily_notes.notes.entity.ShortNoteEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

@Repository
public class ShortNoteDaoImpl implements ShortNoteDao {

    private final DynamoDbTable<ShortNoteEntity> table;

    public ShortNoteDaoImpl(DynamoDbEnhancedClient enhancedClient) {
        this.table = enhancedClient.table(
                "short_note",
                TableSchema.fromBean(ShortNoteEntity.class)
        );
    }

    @Override
    public ShortNoteEntity createShortNote(ShortNoteEntity shortNoteEntity) {
        table.putItem(shortNoteEntity);
        return shortNoteEntity;
    }

    @Override
    public Page<ShortNoteEntity> getAllShortNotes(
            String categoryId,
            String userId,
            int limit,
            Map<String, AttributeValue> lastEvaluatedKey) {

        // 1. Query on Partition Key (userId)
        QueryConditional condition = QueryConditional.keyEqualTo(
                Key.builder().partitionValue(userId).build()
        );

        QueryEnhancedRequest.Builder builder = QueryEnhancedRequest.builder()
                .queryConditional(condition)
                .scanIndexForward(false)
                .limit(limit);

        // 2. Filter by categoryId if provided
        if (categoryId != null && !categoryId.trim().isEmpty()) {
            builder.filterExpression(
                    Expression.builder()
                            .expression("#catId = :catVal")
                            .putExpressionName("#catId", "categoryId")
                            .putExpressionValue(":catVal", AttributeValue.builder().s(categoryId).build())
                            .build()
            );
        }

        if (lastEvaluatedKey != null && !lastEvaluatedKey.isEmpty()) {
            builder.exclusiveStartKey(lastEvaluatedKey);
        }

        // 3. Execute Query
        return table.query(builder.build()).iterator().next();
    }

    @Override
    public void deleteShortNoteById(String category, String userId, String noteId) {
        /*table.deleteItem(Key.builder())
                .partitionValue(userId)
                .sortValue(noteId)
                .build()
        );*/
    }
}