package com.example.trainingstudio.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;

@Entity
public class ThemesDetail {

    @NonNull
    @PrimaryKey
    private int id;
    private String textTitleColor;
    private String backgroundColor;
    private String editTextColor;
    private boolean isSelected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextTitleColor() {
        return textTitleColor;
    }

    public void setTextTitleColor(String textTitleColor) {
        this.textTitleColor = textTitleColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getEditTextColor() {
        return editTextColor;
    }

    public void setEditTextColor(String editTextColor) {
        this.editTextColor = editTextColor;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
