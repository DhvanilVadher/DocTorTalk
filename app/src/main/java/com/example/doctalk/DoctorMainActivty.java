package com.example.doctalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class DoctorMainActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main_activty);
    }

    public void GoTOChat2(View view) {
        Intent intent = new Intent(this,docChat.class);
        startActivity(intent);
    }

    public void LogOut2(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this,MainActivity.class );
        startActivity( intent );
        finish();
    }
}
