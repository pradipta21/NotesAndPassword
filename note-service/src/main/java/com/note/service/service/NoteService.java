package com.note.service.service;

import com.note.service.models.Note;
import com.note.service.repositories.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteService {

    Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    NoteRepository noteRepository;

    public String addNote(Note note){

        note.setId(UUID.randomUUID().toString());
        Date date = new Date();
        note.setCreatedTime(date);
        note.setModifiedTime(date);
        return noteRepository.save(note).getId();
    }

    public Optional<Note> getNote(String id){
        return noteRepository.findById(id);
    }

    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    public boolean updateNote(String id, Note note){

        Optional<Note> updatedNote = getNote(id);
        if(!updatedNote.isPresent()){
            logger.error("Update failed! \nNote with id " + id+ " not found");
            return false;
        }
        updatedNote.get().setTitle(note.getTitle());
        updatedNote.get().setDescription(note.getDescription());
        Date date = new Date();
        updatedNote.get().setModifiedTime(date);
        noteRepository.save(updatedNote.get());
        return true;
    }

    public boolean deleteNote(String id){
        try {
            noteRepository.deleteById(id);
            return true;
        }catch (EmptyResultDataAccessException error){
            logger.error("Delete failed! \nNote not found with id {}", id);
            error.printStackTrace();
            return false;
        }
    }
}
