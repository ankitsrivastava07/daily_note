package com.daily_notes.notes.config;

import com.daily_notes.notes.entity.NoteEntity;
import com.daily_notes.notes.entity.ShortNoteEntity;
import com.daily_notes.notes.entity.WorkSpaceEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.ResourceInUseException;

@Configuration
public class DynamoDbTableInitializer {

    private final DynamoDbEnhancedClient enhancedClient;

    public DynamoDbTableInitializer(
            DynamoDbEnhancedClient enhancedClient) {
        this.enhancedClient = enhancedClient;
    }

    @PostConstruct
    public void createAllTables() {

        createTable("note", NoteEntity.class);
        createTable("short_note", ShortNoteEntity.class);
        createTable("work-space", WorkSpaceEntity.class);

        // add every table here
        // createTable("category", CategoryEntity.class);
        // createTable("todo", TodoEntity.class);
        // createTable("project", ProjectEntity.class);
    }

    private <T> void createTable(
            String tableName,
            Class<T> entityClass) {

        DynamoDbTable<T> table =
                enhancedClient.table(
                        tableName,
                        TableSchema.fromBean(entityClass)
                );

        try {

            table.createTable();

            System.out.println(
                    "DynamoDB TABLE CREATED -> " + tableName
            );

        } catch (ResourceInUseException e) {

            System.out.println(
                    "DynamoDB TABLE ALREADY EXISTS -> " + tableName
            );
        }
    }
}