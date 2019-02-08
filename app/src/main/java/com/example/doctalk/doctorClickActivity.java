package com.example.doctalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class doctorClickActivity extends AppCompatActivity {

    TextView signUptext;

    public void doctorSignup(View view){

        Intent intent=new Intent(getApplicationContext(),doctorSignUpActivity.class);
        startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_click);

        signUptext=findViewById(R.id.doctorSignup);
    }
}
