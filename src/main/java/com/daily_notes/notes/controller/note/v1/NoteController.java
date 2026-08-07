package com.daily_notes.notes.controller.note.v1;

import com.daily_notes.notes.dto.CreateNoteDto;
import com.daily_notes.notes.records.CreateNoteDtoRecord;
import com.daily_notes.notes.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/note")
@RestController
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<?> createNote(@RequestHeader String userId, @RequestBody CreateNoteDtoRecord createNoteDto) {
        return new ResponseEntity<>(noteService.createNote(userId, createNoteDto), HttpStatus.CREATED);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<?> getNoteById(@PathVariable String noteId) {
        return new ResponseEntity<>(noteService.getNoteById(noteId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllNotes(@RequestParam(defaultValue = "0", required = false) Integer offset,
                                         @RequestParam(defaultValue = "10", required = false) Integer limit, @RequestHeader String userId) {
        return new ResponseEntity<>(noteService.getAllNotes(userId, offset, limit), HttpStatus.OK);
    }
}
