package com.example.trainingstudio.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {ThemesDetail.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase sInstance;


    public static synchronized AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ex")
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }


    public abstract ThemesDao themesDao();
}