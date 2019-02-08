package com.example.doctalk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class patientSignUpActivity extends AppCompatActivity {
    DatabaseReference mdb;
    private FirebaseAuth Auth;
    FirebaseUser firebaseUser1;
    private EditText UserName,Password,Email ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);
        Spinner spinner=findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(patientSignUpActivity.this,android.R.layout.simple_list_item_1
                ,getResources().getStringArray(R.array.state));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        Email = findViewById( R.id.patientSignupMail );
        Password = findViewById( R.id.patientSignupMailPassword);
        mdb = FirebaseDatabase.getInstance().getReference();
        Auth = FirebaseAuth.getInstance();
        firebaseUser1 = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser1!=null){
            String a=firebaseUser1.getUid();
            Toast.makeText( patientSignUpActivity.this,a,Toast.LENGTH_SHORT ).show();
            Intent intent = new Intent( patientSignUpActivity.this,docChat.class);
            startActivity( intent );
            finish();
        }
    }

    public void SignUp(View view) {   Auth = FirebaseAuth.getInstance();
        Auth.createUserWithEmailAndPassword( Email.getText().toString(),Password.getText().toString() ).addOnSuccessListener( new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess( AuthResult authResult ) {
                FirebaseUser user = authResult.getUser();
                final String userId= user.getUid();
                DatabaseReference reference = mdb.child( "User" ).child( userId );
                HashMap<String,String> hashMap = new HashMap<>(  );
                hashMap.put("id",userId);
                hashMap.put("Email",Email.getText().toString());
                hashMap.put("PassWord",Password.getText().toString());
                reference.setValue( hashMap ).addOnSuccessListener( new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess( Void aVoid ) {
                        Intent intent = new Intent( patientSignUpActivity.this,docChat.class );
                        intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK );
                        startActivity(intent);
                        finish();
                        Toast.makeText( patientSignUpActivity.this,"Hello"+UserName,Toast.LENGTH_SHORT).show();
                    }
                } ).addOnFailureListener( new OnFailureListener() {
                    @Override
                    public void onFailure( @NonNull Exception e ) {
                        Toast.makeText( patientSignUpActivity.this,"You can't register",Toast.LENGTH_SHORT).show();
                    }
                } );
            }
        } ).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure( @NonNull Exception e ) {
                Toast.makeText( patientSignUpActivity.this,"Faild!Something went wrong",Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
