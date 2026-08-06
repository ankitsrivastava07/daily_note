package com.daily_notes.notes.repository.read;

import com.daily_notes.notes.entity.NoteEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface NoteRepository extends CassandraRepository<NoteEntity, String> {

    @Query("delete from note where id=?0 and user_id=?1")
    void deleteNoteById(String noteId, String userID);

    List<NoteEntity> findByUserId(String userId, Pageable pageable);
}
