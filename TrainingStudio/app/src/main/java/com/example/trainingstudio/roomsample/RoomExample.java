package com.example.trainingstudio.roomsample;

import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.trainingstudio.R;

public class RoomExample extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static SampleDb sampleDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_example);

        sampleDb = Room.databaseBuilder(getApplicationContext(), SampleDb.class, "user").allowMainThreadQueries().build();
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.RoomExample, new FirstFragment()).commit();

    }
}
