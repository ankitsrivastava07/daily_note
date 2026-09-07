package com.daily_notes.notes.service;

import com.daily_notes.notes.dao.ShortNoteDao;
import com.daily_notes.notes.dto.CreateShortNoteDto;
import com.daily_notes.notes.dto.NoteResponseDto;
import com.daily_notes.notes.entity.ShortNoteEntity;
import com.daily_notes.notes.mapper.CustomMapper;
import com.daily_notes.notes.records.ApiResponse;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import java.util.Map;

@Service
public class ShortNoteServiceImpl implements ShortNoteService {

    private final ShortNoteDao shortNoteDao;

    public ShortNoteServiceImpl(ShortNoteDao shortNoteDao) {
        this.shortNoteDao = shortNoteDao;
    }

    @Override
    public ApiResponse createShortNote(CreateShortNoteDto createShortNoteDto) {
        ShortNoteEntity entity = CustomMapper
                .mapToEntity(createShortNoteDto,
                        ShortNoteEntity.class);
        entity.setId(UUID.randomUUID().toString());
        entity = shortNoteDao
                .createShortNote(entity);

        return new ApiResponse()
                .data(entity)
                .success(Boolean.TRUE)
                .message("Success");
    }

    @Override
    public ApiResponse getAllShortNotes(
            String categoryId1,
            String userId,
            Integer limit,
            Map<String, AttributeValue> attributeValueMap) {

        Page<ShortNoteEntity> sdkPage =
                shortNoteDao.getAllShortNotes(
                        userId,
                        limit,
                        attributeValueMap
                );

        List<ShortNoteEntity> rawItems = (sdkPage != null && sdkPage.items() != null)
                ? new ArrayList<>(sdkPage.items())
                : Collections.emptyList();

        // DEBUG 1: Inspect what DynamoDB returned
        System.out.println("=== DEBUG: RAW ITEMS FROM DYNAMODB ===");
        System.out.println("Raw Items Count: " + rawItems.size());
        rawItems.forEach(item -> System.out.println("ID: " + item.getId() + " | CreatedAt: " + item.getCreatedAt()));

        // Perform sort
        rawItems.sort((n1, n2) -> {
            String t1 = String.valueOf(n1.getCreatedAt());
            String t2 = String.valueOf(n2.getCreatedAt());
            if (t1 == null) return 1;
            if (t2 == null) return -1;
            return t2.compareTo(t1); // Reverse order
        });

        // DEBUG 2: Inspect items after Java sort
        System.out.println("=== DEBUG: ITEMS AFTER JAVA SORT ===");
        rawItems.forEach(item -> System.out.println("ID: " + item.getId() + " | CreatedAt: " + item.getCreatedAt()));

        NoteResponseDto<ShortNoteEntity> response =
                NoteResponseDto.<ShortNoteEntity>builder()
                        .items(rawItems)
                        .lastEvaluatedKey(sdkPage != null ? sdkPage.lastEvaluatedKey() : Collections.emptyMap())
                        .build();

        return new ApiResponse()
                .data(response)
                .message("Success")
                .success(Boolean.TRUE);
    }

    @Override
    public ApiResponse deleteShortNoteById(String category, String userId, String noteId) {
        shortNoteDao.deleteShortNoteById(category, userId, noteId);
        return new ApiResponse().success(true);
    }
}
