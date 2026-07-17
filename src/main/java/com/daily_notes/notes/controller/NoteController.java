package com.daily_notes.notes.controller;

import com.daily_notes.notes.records.CreateNoteDtoRecord;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/note/")
public class NoteController {

    @PostMapping("/create")
    public ResponseEntity<?> createNote(@RequestBody @Valid CreateNoteDtoRecord createNoteDtoRecord) {

        return null;
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<?> getNoteById(@PathVariable String noteId) {

        return null;
    }

}
