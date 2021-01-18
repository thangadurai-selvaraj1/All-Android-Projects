package com.example.trainingstudio.roomsample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.trainingstudio.db.ThemesDetail;

@Database(entities = {SampleAddData.class}, version = 1, exportSchema = false)
public abstract class SampleDb  extends RoomDatabase {
    public abstract SampleDao sampleDao();

}
