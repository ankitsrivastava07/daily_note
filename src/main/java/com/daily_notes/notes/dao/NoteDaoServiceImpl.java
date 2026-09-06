package com.daily_notes.notes.dao;

import com.daily_notes.notes.entity.NoteEntity;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Repository
public class NoteDaoServiceImpl implements NoteDaoService {

    // private final NoteRepository noteRepository;
    private DynamoDbTemplate dynamoDbTemplate;

    public NoteDaoServiceImpl(DynamoDbTemplate dynamoDBTemplate) {
        this.dynamoDbTemplate = dynamoDBTemplate;
    }

    @Override
    public NoteEntity createNote(NoteEntity noteEntity) {
        noteEntity.setId(UUID.randomUUID().toString());
        return dynamoDbTemplate.save(noteEntity);
    }

    @Override
    public void deleteNote(String noteId, String userId) {
        Key key = Key.builder()
                .partitionValue(userId) // Partition Key
                .sortValue(noteId)      // Sort Key
                .build();
        dynamoDbTemplate.delete(key, NoteEntity.class);
    }

    @Override
    public NoteEntity updateNote(NoteEntity noteEntity) {
        return dynamoDbTemplate.save(noteEntity);
    }

    @Override
    public List<NoteEntity> getAllNotes(String userId, Pageable pageable) {
        QueryConditional queryConditional =
                QueryConditional.keyEqualTo(
                        Key.builder()
                                .partitionValue(userId)
                                .build()
                );

        QueryEnhancedRequest request =
                QueryEnhancedRequest.builder()
                        .queryConditional(queryConditional)
                        .build();

        return dynamoDbTemplate
                .query(request, NoteEntity.class)
                .items()
                .stream()
                .toList();
    }

    @Override
    public NoteEntity getNoteById(String noteId, String userId) {

        Key key = Key.builder()
                .partitionValue(userId)
                .sortValue(noteId)
                .build();

        return dynamoDbTemplate.load(
                key,
                NoteEntity.class
        );
    }
}
