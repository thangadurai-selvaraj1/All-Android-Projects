package com.example.trainingstudio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.trainingstudio.db.Adapter;
import com.example.trainingstudio.db.AppDatabase;
import com.example.trainingstudio.db.ThemesDetail;
import com.example.trainingstudio.roomsample.RoomExample;

import java.util.ArrayList;
import java.util.List;

public class ListOfColors extends AppCompatActivity {
    private AppDatabase db;
    RecyclerView recyclerView;
    Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<ThemesDetail> themesDetails = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_colors);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        db = AppDatabase.getInstance(this);

        themesDetails = db.themesDao().fetchAllTheme();
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(themesDetails, this, new Adapter.Communication() {
            @Override
            public void success(int oldPosition,int newPosition) {

                themesDetails.get(oldPosition).setSelected(false);

                themesDetails.set(oldPosition,  themesDetails.get(oldPosition));

                themesDetails.get(newPosition).setSelected(true);

                themesDetails.set(newPosition,  themesDetails.get(newPosition));

                db.themesDao().updateTheme( themesDetails.get(oldPosition));
                db.themesDao().updateTheme( themesDetails.get(newPosition));

                openThemeScreen();
            }


        });
        recyclerView.setAdapter(adapter);



       /* adapter.setOnClickListener(new Adapter.Communication() {
            @Override
            public void success(ThemesDetail theme) {
                for (int i = 0; i < themesDetail.size(); i++) {
                    if (themesDetail.get(i).getId() != theme.getId()) {

                        if(themesDetail.get(i).isSelected()){
                            themesDetail.get(i).setSelected(false);
                        }

                    } else {
                        theme.setSelected(true);
                        db.themesDao().updateTheme(theme);

                        // themesDetail1.setSelected(false);
                    }
                }

                Toast.makeText(ListOfColors.this, "pos : " , Toast.LENGTH_SHORT).show();
            }
        });*/


    }


    private void openThemeScreen(){

        startActivity(new Intent(this, MainActivity2.class));
    }

}
