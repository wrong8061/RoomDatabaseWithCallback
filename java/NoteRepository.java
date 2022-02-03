package com.example.architectureapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database=NoteDatabase.getInstance(application);
        noteDao=database.noteDao();
        allNotes=noteDao.getAllNotes();
    }

    public void insert(Note note){
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteDao.insert(note);
            }
        });
    }
    public void update(Note note){
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteDao.update(note);
            }
        });
    }
    public void delete(Note note){
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteDao.delete(note);
            }
        });
    }
    public void deleteAllNotes(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteDao.delelteAllNotes();
            }
        });
    }
    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

}
