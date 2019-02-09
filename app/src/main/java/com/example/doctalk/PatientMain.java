package com.example.doctalk;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class PatientMain extends AppCompatActivity {

    ImageView fb,ins,in,tt,yt;

    public void yt( View view){
        yt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.youtube.com/channel/UCny6KCh8xFAjjBkl3mtW2GQ/featured/"));
                startActivity(viewIntent);
            }
        });

    }
    public void in( View view){
        in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.linkedin.com/in/doc-talk-17b59917a/"));
                startActivity(viewIntent);
            }
        });

    }
    public void ins( View view){
        ins.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.instagram.com/doctalk.pvt.ltd/"));
                startActivity(viewIntent);
            }
        });
    }
    public void fb( View view){
        fb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.facebook.com/ourdoctalk"));
                startActivity(viewIntent);
            }
        });
    }
    public void tt( View view){
        tt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://twitter.com/DocTalk4"));
                startActivity(viewIntent);
            }
        });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);
        fb=findViewById( R.id.fb );
        ins=findViewById( R.id.ins );
        in=findViewById( R.id.in );
        tt=findViewById( R.id.tt );
        yt=findViewById( R.id.yt );
    }

    public void LogOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(PatientMain.this,MainActivity.class );
        startActivity( intent );
        finish();
    }
    public void GoToChat(View view) {
        Intent intent= new Intent(this,DoctorMainActivty.class);
        startActivity(intent);
    }
    public void GoToBot(View view) {
        Intent intent = new Intent(this,chatBotActivity.class);
        startActivity(intent);
    }
}
