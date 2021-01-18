package com.example.trainingstudio.roomsample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SampleDao {

    @Insert
    void insert(SampleAddData sampleAddData);

    @Query("select * from SampleAddData")
    public List<SampleAddData> getData();

    @Delete
    public void delete(SampleAddData sampleAddData);

    @Update
    public void update(SampleAddData sampleAddData);
}
