package com.note.service.repositories;

import com.note.service.models.Note;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class NoteRepositoryImp{

    List<Note> notes = new ArrayList<>();


    public String save(Note note) {
        note.setId(UUID.randomUUID().toString());
        Date date = new Date();
        note.setCreatedTime(date);
        note.setModifiedTime(date);
        notes.add(note);
        return note.getId();
    }

    public Optional<Note> get(String id) {
        return notes.stream().filter(note -> note.getId().equals(id)).findFirst();
    }
}
