package com.example.trainingstudio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.trainingstudio.db.AppDatabase;
import com.example.trainingstudio.db.ThemesDetail;

import java.util.ArrayList;
import java.util.List;

public class ThemesActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);

        db = AppDatabase.getInstance(this);
    }

    public void onThemeSave(View view) {
        List<ThemesDetail> themesDetailList = db.themesDao().fetchAllTheme();
        db.themesDao().deleteThemeList(themesDetailList);
        db.themesDao().insertTheme(getThemeLists());
    }

    public void onGetTheme(View view) {

        startActivity(new Intent(this, ListOfColors.class));

    }

    private List<ThemesDetail> getThemeLists() {

        List<ThemesDetail> themesDetailList = new ArrayList<>();

        ThemesDetail themesDetail = new ThemesDetail();
        themesDetail.setId(1);
        themesDetail.setTextTitleColor("#FF5733");
        themesDetail.setEditTextColor("#A2F58F");
        themesDetail.setBackgroundColor("#39DEB9");
        themesDetail.setSelected(true);

        ThemesDetail themesDetail1 = new ThemesDetail();
        themesDetail1.setId(2);
        themesDetail1.setTextTitleColor("#9339DE");
        themesDetail1.setEditTextColor("#D739DE");
        themesDetail1.setBackgroundColor("#F10E6B");
        themesDetail1.setSelected(false);

        ThemesDetail themesDetail2 = new ThemesDetail();
        themesDetail2.setId(3);
        themesDetail2.setTextTitleColor("#F1D60E");
        themesDetail2.setEditTextColor("#F1370E");
        themesDetail2.setBackgroundColor("#C8F10E");
        themesDetail2.setSelected(false);

        ThemesDetail themesDetail3 = new ThemesDetail();
        themesDetail3.setId(4);
        themesDetail3.setTextTitleColor("#0070FF");
        themesDetail3.setEditTextColor("#551515");
        themesDetail3.setBackgroundColor("#13FF00");
        themesDetail3.setSelected(false);

        themesDetailList.add(themesDetail);
        themesDetailList.add(themesDetail1);
        themesDetailList.add(themesDetail2);
        themesDetailList.add(themesDetail3);

        return themesDetailList;
    }
}
