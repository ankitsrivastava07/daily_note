package com.daily_notes.notes.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Getter
@Setter
@Table("note")
public class NoteEntity {

    @PrimaryKey
    private String id;

    @Column("attachment_ids")
    private List<String> attachments;

    @Column(value = "user_id")
    private String userId;

    @Column("title")
    private String title;
    @Column("content")
    private String content;

    @Column("created_at")
    private String createdAt;
    @Column("updated_at")
    private String updatedAt;

    @Column("created_by")
    private String createdBy;
    @Column("updated_by")
    private String updatedBy;

    private List<String> todoIds;
}
