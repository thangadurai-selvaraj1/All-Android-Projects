package com.example.trainingstudio.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ThemesDao {

    @Insert
    void insertTheme(List<ThemesDetail> themesDetail);

    @Query("SELECT * FROM themesdetail")
    List<ThemesDetail> fetchAllTheme();

    @Query("SELECT * FROM themesdetail WHERE isSelected = :isSelected")
    ThemesDetail fetchTheme(boolean isSelected);

    @Update
    void updateTheme(ThemesDetail themesDetail);

    @Delete
    void deleteThemeList(List<ThemesDetail> themesDetailList);
}