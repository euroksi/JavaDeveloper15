package com.example.todolist.service;

import com.example.todolist.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NoteService {
    private final Map<Long, Note> notes = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    public Note add(Note note) {
        long id = idGenerator.incrementAndGet();
        note.setId(id);
        notes.put(id, note);
        return note;
    }

    public void deleteById(long id) {
        if (!notes.containsKey(id)) {
            throw new RuntimeException("Note with id " + id + " not found");
        }
        notes.remove(id);
    }

    public void update(Note note) {
        Long id = note.getId();
        if (!notes.containsKey(id)) {
            throw new RuntimeException("Note with id " + id + " not found");
        }
        notes.put(id, note);
    }

    public Note getById(long id) {
        Note note = notes.get(id);
        if (note == null) {
            throw new RuntimeException("Note with id " + id + " not found");
        }
        return note;
    }
}