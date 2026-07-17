package com.daily_notes.notes.records;

public record ApiResponse(String message, Object data,
                          Boolean flag,
                          Object error) {


}
