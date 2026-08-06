package com.daily_notes.notes.dao;

import com.daily_notes.notes.entity.NoteEntity;
import com.daily_notes.notes.repository.read.NoteRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NoteDaoServiceImpl implements NoteDaoService {

    private final NoteRepository noteRepository;

    public NoteDaoServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public NoteEntity createNote(NoteEntity noteEntity) {
        return noteRepository.save(noteEntity);
    }

    @Override
    public void deleteNote(String noteId, String userId) {
        noteRepository.deleteNoteById(noteId, userId);
    }

    @Override
    public void updateNote(NoteEntity noteEntity) {
        noteRepository.save(noteEntity);
    }

    @Override
    public List<NoteEntity> getAllNotes(String userId, Pageable pageable) {
        return noteRepository.findByUserId(userId, pageable);
    }

    @Override
    public Optional<NoteEntity> getNoteById(String noteId) {
        return noteRepository.findById(noteId);
    }
}
