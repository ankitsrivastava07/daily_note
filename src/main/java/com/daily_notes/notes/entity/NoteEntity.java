package com.daily_notes.notes.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Table("note")
public class NoteEntity {

    @PrimaryKeyColumn(
            name = "user_id",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED
    )
    private String userId;


    @PrimaryKeyColumn(
            name = "created_at",
            ordinal = 1,
            type = PrimaryKeyType.CLUSTERED
    )
    private Instant createdAt;


    @PrimaryKeyColumn(
            name = "_id",
            ordinal = 2,
            type = PrimaryKeyType.CLUSTERED
    )
    private String id;


    @Column("title")
    private String title;


    @Column("content")
    private String content;


    @Column("attachment_ids")
    private List<String> attachments;


    @Column("updated_at")
    private Instant updatedAt;


    @Column("created_by")
    private String createdBy;


    @Column("updated_by")
    private String updatedBy;


    @Column("todo_ids")
    private List<String> todoIds;
}