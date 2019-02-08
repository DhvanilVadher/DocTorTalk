package com.example.doctalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView doctor;
    ImageView patient;


    public void doctorClick(View view){

        doctor.animate().scaleX(1.5f).scaleY(1.5f).setDuration(500);
        doctor.animate().alpha(1f).setDuration(500);
        patient.animate().alpha(0.5f).setDuration(500);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // this code will be executed after 0.5 seconds
                Intent intent=new Intent(getApplicationContext(),doctorClickActivity.class);
                startActivity(intent);
            }
        }, 500);

    }

    public void patientClick(View view){

        patient.animate().scaleX(1.5f).scaleY(1.5f).setDuration(500);
        patient.animate().alpha(1f).setDuration(500);
        doctor.animate().alpha(0.5f).setDuration(500);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // this code will be executed after 0.5 seconds
                Intent intent =new Intent(getApplicationContext(),patientClickActivity.class);
                startActivity(intent);

            }
        }, 500);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doctor=findViewById(R.id.doctor_front);
        patient=findViewById(R.id.patient_front);



    }
}
