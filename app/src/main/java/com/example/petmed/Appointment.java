package com.example.petmed;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Appointment extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnTimePicker,setbtn;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    DatabaseReference path;
    EditText pname,type,reason,symptoms;
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        getSupportActionBar().setTitle("Appointment");


        pname = findViewById(R.id.Pets);
        type = findViewById(R.id.Breed);
        reason = findViewById(R.id.Reason1);
        symptoms = findViewById(R.id.symptoms);
        setbtn = findViewById(R.id.setbtn);


        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        setbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getvalue();

            }


        });



    }



    private void getvalue() {

        String strname = pname.getText().toString().trim();
        String strtype = type.getText().toString().trim();
        String strreason = reason.getText().toString().trim();
        String strsymptoms = symptoms.getText().toString().trim();
        String strdate = txtDate.getText().toString().trim();
        String strtime = txtTime.getText().toString().trim();
        FirebaseUser user = auth.getInstance().getCurrentUser();
        String uid = user.getUid();
        getappointmentstring value = new getappointmentstring(strname,strtype,strreason,strsymptoms,strdate,strtime,uid);



        path = FirebaseDatabase.getInstance().getReference().child("appointment");
        path.push().setValue(value);
        Toast.makeText(Appointment.this,"Scheduling..., Success!", Toast.LENGTH_LONG).show();


        pname.getText().clear();
        type.getText().clear();
        reason.getText().clear();
        txtDate.getText().clear();
        txtTime.getText().clear();
        symptoms.getText().clear();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

}



