package com.daily_notes.notes.dao;

import com.daily_notes.notes.entity.NoteEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteDaoService {

    NoteEntity createNote(NoteEntity noteEntity);

    void deleteNote(String noteId, String userId);

    void updateNote(NoteEntity noteEntity);

    List<NoteEntity> getAllNotes(String userId, Pageable pageable);

    Optional<NoteEntity> getNoteById(String noteId);
}
