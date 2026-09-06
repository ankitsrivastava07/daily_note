/*
package com.daily_notes.notes.config;

import com.daily_notes.notes.entity.NoteEntity;
import com.daily_notes.notes.entity.ShortNoteEntity;
import io.awspring.cloud.dynamodb.DynamoDbTableNameResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbTableNameConfig {

    @Bean
    public DynamoDbTableNameResolver dynamoDbTableNameResolver() {

        return new DynamoDbTableNameResolver() {

            @Override
            public <T> String resolve(Class<T> clazz) {

                if (clazz.equals(NoteEntity.class)) {
                    return "note";
                }

                if (clazz.equals(ShortNoteEntity.class)) {
                    return "short_note";
                }

                throw new IllegalArgumentException(
                        "No DynamoDB table configured for: " + clazz.getName()
                );
            }
        };
    }
}*/
