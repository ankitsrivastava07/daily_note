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

import java.util.Map;
import java.util.UUID;

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
                .status(Boolean.TRUE)
                .message("Success");
    }

    @Override
    public ApiResponse getAllShortNotes(String categoryId, String userId, Integer limit, Map<String, AttributeValue> attributeValueMap) {

        Page<ShortNoteEntity> sdkPage = shortNoteDao.getAllShortNotes(categoryId, userId, limit, null);

        NoteResponseDto<ShortNoteEntity> response = NoteResponseDto.<ShortNoteEntity>builder()
                .items(sdkPage.items()) // Unwraps the list of 2 ShortNoteEntity items
                .lastEvaluatedKey(sdkPage.lastEvaluatedKey())
                .build();

        return new ApiResponse()
                .data(response)
                .message("Success")
                .status(Boolean.TRUE);
    }

    @Override
    public ApiResponse deleteShortNoteById(String category, String userId, String noteId) {

        shortNoteDao.deleteShortNoteById(category, userId, noteId);

        return null;
    }
}
