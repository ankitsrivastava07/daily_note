package com.daily_notes.notes.service;

import com.daily_notes.notes.dto.CreateNoteDto;
import com.daily_notes.notes.dto.UpdateNoteDto;
import com.daily_notes.notes.records.ApiResponse;

public interface NoteService {

    ApiResponse createNote(CreateNoteDto createNoteDto);

    ApiResponse deleteNoteById(String id, String userId);

    ApiResponse getAllNotes(String userId, Integer page, Integer limit);

    ApiResponse updateNote(UpdateNoteDto updateNoteDto);

    ApiResponse getNoteById(String id);
}
