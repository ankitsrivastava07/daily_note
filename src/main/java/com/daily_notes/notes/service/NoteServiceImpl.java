package com.daily_notes.notes.service;

import com.daily_notes.notes.dao.NoteDaoService;
import com.daily_notes.notes.dto.UpdateNoteDto;
import com.daily_notes.notes.entity.NoteEntity;
import com.daily_notes.notes.mapper.CustomMapper;
import com.daily_notes.notes.records.ApiResponse;
import com.daily_notes.notes.records.CreateNoteDtoRecord;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

import com.daily_notes.notes.exceptions.exception.NoteNotFoundException;

import static com.daily_notes.notes.utility.constant.NoteConstant.NOTE_NOT_FOUND;
import static com.daily_notes.notes.utility.constant.NoteConstant.SUCCESS;

@Service

public class NoteServiceImpl implements NoteService {

    private final NoteDaoService noteDaoService;

    public NoteServiceImpl(NoteDaoService noteDaoService) {
        this.noteDaoService = noteDaoService;
    }

    @Override
    public ApiResponse createNote(String userId, CreateNoteDtoRecord createNoteDto) {
        NoteEntity noteEntity = CustomMapper.mapToEntity(createNoteDto, NoteEntity.class);
        noteEntity.setUserId(userId);
        noteEntity = noteDaoService.createNote(noteEntity);
        return new ApiResponse("Success", noteEntity, true, new ArrayList<>());
    }

    @Override
    public ApiResponse getNoteById(String noteId) {
        NoteEntity note = noteDaoService.getNoteById(noteId).orElseThrow(() ->
                new NoteNotFoundException(NOTE_NOT_FOUND + noteId));

        return new ApiResponse("Success", note, true, new ArrayList<>());
    }

    @Override
    public ApiResponse updateNote(UpdateNoteDto updateNoteDto) {
        NoteEntity note = noteDaoService.getNoteById(updateNoteDto.id()).orElseThrow(() ->
                new NoteNotFoundException(NOTE_NOT_FOUND + updateNoteDto.id()));

        noteDaoService.updateNote(note);
        NoteEntity noteEntity = noteDaoService.getNoteById(note.getId())
                .orElseThrow(() ->
                        new NoteNotFoundException(NOTE_NOT_FOUND));
        return new ApiResponse(SUCCESS, noteEntity, true, new ArrayList<>());
    }

    @Override
    public ApiResponse deleteNoteById(String id, String userId) {
        noteDaoService.deleteNote(id, userId);
        return new ApiResponse(SUCCESS, noteDaoService.getNoteById(id), true, new ArrayList<>());
    }

    @Override
    public ApiResponse getAllNotes(String userId, Integer offset, Integer limit) {

        Pageable pageable = PageRequest.of(offset,
                limit, Sort.by(Sort.Direction.DESC, "created_at"));
        return new ApiResponse(SUCCESS, noteDaoService.getAllNotes(userId, pageable),
                true, new ArrayList<>());
    }
}
