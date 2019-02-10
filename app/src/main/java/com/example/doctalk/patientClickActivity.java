package com.example.doctalk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class patientClickActivity extends AppCompatActivity {


    //initialization
    DatabaseReference mdb;
    FirebaseAuth Auth;
    FirebaseUser firebaseUser;
    TextView signUptext;
    EditText email,pwd;
    String Email,Pwd;
    FirebaseUser firebaseUser1;

    public void patientSignup(View view){
        Intent intent=new Intent(getApplicationContext(),patientSignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_click);

        //Assignment
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
            Toast.makeText( patientClickActivity.this,a,Toast.LENGTH_SHORT ).show();
            Intent intent = new Intent( patientClickActivity.this,PatientMain.class);
            startActivity( intent );
            finish();
        }
    }
    public void login1(View view) {
        //name says it all
        String Email,Pwd;
        Email = email.getText().toString();
        Pwd = pwd.getText().toString();
        Auth.signInWithEmailAndPassword( Email,Pwd ).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess( AuthResult authResult ) {
                Intent intent= new Intent(patientClickActivity.this,PatientMain.class );
                startActivity( intent );
                finish();
            }
        }).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure( @NonNull Exception e ) {
                Toast.makeText( patientClickActivity.this,"Enter Credentials Properly",Toast.LENGTH_LONG).show();
            }
        } );
    }
}
