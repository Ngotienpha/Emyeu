package com.example.contactdatabase;

import static android.media.CamcorderProfile.get;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button save, details;
    EditText name, dob, email;
    Calendar myCalendar;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dob = findViewById(R.id.editdate);
        name = findViewById(R.id.editname);
        email = findViewById(R.id.editemail);
        save = findViewById(R.id.save);
        DB = new DBHelper(this);
        myCalendar = Calendar.getInstance();
        details = findViewById(R.id.details);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();
                String dobTxt = dob.getText().toString();
                String emailTxt = email.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTxt, dobTxt, emailTxt);
                if(checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "New entry saved", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "New entry not saved", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });
       DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker datePicker, int year, int month, int day) {
               myCalendar.set(Calendar.YEAR, year);
               myCalendar.set(Calendar.MONTH, month);
               myCalendar.set(Calendar.DAY_OF_MONTH, day);
               updateLabel();
           }
       };

       dob.setOnClickListener(view -> {
           new DatePickerDialog(MainActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });


    }

    private void updateLabel() {
        String myFormat="MM/dd/yy EEEE";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        dob.setText(dateFormat.format(myCalendar.getTime()));
    }
}