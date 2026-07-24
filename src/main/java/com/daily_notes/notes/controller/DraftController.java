package com.daily_notes.notes.controller;

import com.daily_notes.notes.records.CreateNoteDtoRecord;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/note/draft")
public class DraftController {

    @GetMapping("/{draftId}")
    public ResponseEntity<?> getDraftById(@PathVariable String draftId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody @Valid CreateNoteDtoRecord createNoteDtoRecord) {
        return null;
    }
}
