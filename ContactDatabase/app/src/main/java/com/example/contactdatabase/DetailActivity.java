package com.example.contactdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> name, dob, email;
    DBHelper DB;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DB = new DBHelper(this);
        name = new ArrayList<>();
        dob = new ArrayList<>();
        email = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, name, dob, email);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }
    private void displaydata(){
        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0){
            Toast.makeText(DetailActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            while (cursor.moveToNext()){
                name.add(cursor.getString(0));
                dob.add(cursor.getString(1));
                email.add(cursor.getString(2));
            }
        }
    }
}