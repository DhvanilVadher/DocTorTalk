package com.example.doctalk;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class DoctorMainActivty extends AppCompatActivity {

    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main_activty);

        updateButton=findViewById(R.id.updateButton);

    }

    public void updateButton( View view){
        //Going to FeedBack Form
        updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW", Uri.parse("https://docs.google.com/document/d/1U1CxJ-1T7Uow6GWtA-4c3Q1KFaI--hiFdei6foi1F8g/edit?usp=sharing"));
                startActivity(viewIntent);
            }
        });
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
