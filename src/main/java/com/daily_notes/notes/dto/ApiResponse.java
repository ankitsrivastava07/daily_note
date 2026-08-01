package com.daily_notes.notes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    private String message;
    private Object data;
    private Object error;
    private Boolean flag;
}
