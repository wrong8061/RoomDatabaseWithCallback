package com.example.architectureapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.TaskExecutor;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 3)
public abstract class NoteDatabase extends RoomDatabase {

    public static NoteDatabase instance=null;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class ,"note_database")
                    .addCallback(callback)
                    .addMigrations(MIGRATION_2_3)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
    private static RoomDatabase.Callback callback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new popuLateDb(instance).execute();
        }
    };


    public static class populateDb{
    }


    public static class popuLateDb extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        public popuLateDb(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title4","Description4",4));
            noteDao.insert(new Note("Title5","Description5",5));
            return null;
        }
    }

}
