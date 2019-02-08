package com.example.doctalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class patientClickActivity extends AppCompatActivity {

    TextView textView;

    public void patientSignup(View view){
        Intent intent=new Intent(getApplicationContext(),patientSignUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_click);

        textView=findViewById(R.id.patientSignup);
    }
}
