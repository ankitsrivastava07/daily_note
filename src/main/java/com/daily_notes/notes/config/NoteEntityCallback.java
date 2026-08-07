package com.daily_notes.notes.config;

import com.daily_notes.notes.entity.NoteEntity;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import lombok.NonNull;
import org.springframework.data.cassandra.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NoteEntityCallback implements BeforeConvertCallback<NoteEntity> {
    @Override
    public @NonNull NoteEntity onBeforeConvert(NoteEntity entity, CqlIdentifier tableName) {
        entity.set_id(UUID.randomUUID().toString());
        return entity;
    }
}
