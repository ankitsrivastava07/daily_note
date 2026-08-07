package com.daily_notes.notes.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Table("note")
public class NoteEntity {

    @PrimaryKeyColumn(
            name = "_id",
            type = PrimaryKeyType.CLUSTERED
    )
    private String _id;

    @Column("attachment_ids")
    private List<String> attachments;
    // @PrimaryKey
    @PrimaryKeyColumn(
            name = "user_id",
            type = PrimaryKeyType.PARTITIONED
    )
    private String userId;

    @Column("title")
    private String title;
    @Column("content")
    private String content;

    @Column("created_at")
    // @PrimaryKey
    private String createdAt;
    @Column("updated_at")
    private String updatedAt;

    @Column("created_by")
    private String createdBy;
    @Column("updated_by")
    private String updatedBy;

    private List<String> todoIds;
}
