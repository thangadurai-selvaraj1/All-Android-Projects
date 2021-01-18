package com.example.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class ListView extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    EditText editText;
    Button button;
    int position;
    boolean check = false;
    ArrayList<Model> models;
    Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

//           model = new Model(models);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        models = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                validDate(name);
                editText.setText("");
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(models, this, new OnClickListener() {
            @Override
            public void onItem(int postion, Model model, String name) {
                editText.setText(name);
                position = postion;
                check = true;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    void validDate(String name) {
        if (name.equals("")) {
            editText.setError("Pls Enter Text");

        } else {
            if (check) {
                Model model = new Model(name);
                models.set(position, model);
                adapter.notifyDataSetChanged();
                check = false;
            } else {
                models.add(new Model(name));
                adapter.notifyDataSetChanged();
            }
        }
    }

}

  /*  public class ClickEvent{

        private EditText name;

        public ClickEvent(EditText name) {
            this.name = name;
        }

        public void clickable(View view){

            model.setName(name.getText().toString());

        }
    }*/



