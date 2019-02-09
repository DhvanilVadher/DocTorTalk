package com.example.doctalk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class doctorClickActivity extends AppCompatActivity {
    DatabaseReference mdb;
    FirebaseAuth Auth;
    FirebaseUser firebaseUser;
    TextView signUptext;
    EditText email,pwd;
    String Email,Pwd;
    FirebaseUser firebaseUser1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_click);
        signUptext=findViewById(R.id.doctorSignup);
        email = findViewById(R.id.mail);
        pwd = findViewById(R.id.password);
        mdb = FirebaseDatabase.getInstance().getReference();
        Auth = FirebaseAuth.getInstance();
        Email  = email.getText().toString();
        Pwd = pwd.getText().toString();
        firebaseUser1 = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser1!=null){
            String a=firebaseUser1.getUid();
            Toast.makeText( doctorClickActivity.this,a,Toast.LENGTH_SHORT ).show();
            Intent intent = new Intent( doctorClickActivity.this,DoctorMainActivty.class);
            startActivity( intent );
            finish();
        }
    }
    public void doctorSignup(View view){
        Intent intent=new Intent(getApplicationContext(),doctorSignUpActivity.class);
        startActivity(intent);
    }
    public void login(View view){
        String Email,Pwd;
        Email = email.getText().toString();
        Pwd = pwd.getText().toString();
        Auth.signInWithEmailAndPassword( Email,Pwd ).addOnSuccessListener( new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess( AuthResult authResult ) {
                Intent intent= new Intent(doctorClickActivity.this,docChat.class );
                startActivity( intent );
                finish();
            }
        }).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure( @NonNull Exception e ) {
                Toast.makeText( doctorClickActivity.this,"Enter Credentials Properly",Toast.LENGTH_LONG).show();
            }
        } );
    }
}
