package com.daily_notes.notes.records;

import com.daily_notes.notes.dto.FileAttachmentDto;

public record CreateNoteDtoRecord(String title,
                                  String name,
                                  String description,
                                  String userId,
                                  FileAttachmentDto fileAttachmentDto) {
}
