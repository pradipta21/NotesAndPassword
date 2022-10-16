package com.note.service.controller;

import com.note.service.Response.NoteResponse;
import com.note.service.models.Note;
import com.note.service.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteResponse> addNote(@RequestBody Note note){

        String id = noteService.addNote(note);
        NoteResponse noteResponse = new NoteResponse("Note added successfully", id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(noteResponse);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<NoteResponse> getNote(@PathVariable("id") String id){

        NoteResponse noteResponse = null;
        HttpStatus status = HttpStatus.NOT_FOUND;

        Optional<Note> note = noteService.getNote(id);
        if(note.isPresent()) {
            noteResponse = new NoteResponse("Note fetched successfully", note.get().getId(), note.get());
            status = HttpStatus.OK;
        }else{
            noteResponse = new NoteResponse("Note not found with id: " + id);
        }
        return ResponseEntity
                .status(status)
                .body(noteResponse);
    }

    @GetMapping()
    public ResponseEntity<NoteResponse> getAllNotes(){

        NoteResponse noteResponse = null;
        HttpStatus status = HttpStatus.NOT_FOUND;

        List<Note> notes = noteService.getAllNotes();
        if(notes.size() > 0){
            noteResponse = new NoteResponse("All Notes fetched successfully", null, notes);
            status = HttpStatus.OK;
        }else{
            noteResponse = new NoteResponse("No notes available");
        }
        return ResponseEntity
                .status(status)
                .body(noteResponse);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable("id") String id, @RequestBody Note note){

        NoteResponse noteResponse = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        boolean updated = noteService.updateNote(id, note);
        if(updated){
            noteResponse = new NoteResponse("Note updated successfully", id, null);
            status = HttpStatus.OK;
        }else {
            noteResponse = new NoteResponse("Note cannot be updated as note with id " + id + " not available");
        }
        return ResponseEntity
                .status(status)
                .body(noteResponse);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<NoteResponse> deleteNote(@PathVariable("id") String id){

        NoteResponse noteResponse = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        boolean deleted = noteService.deleteNote(id);
        if(deleted){
            noteResponse = new NoteResponse("Note deleted successfully", null, null);
            status = HttpStatus.OK;
        }else {
            noteResponse = new NoteResponse("Note cannot be deleted as note with id " + id + " not available");
        }
        return ResponseEntity
                .status(status)
                .body(noteResponse);
    }

}
