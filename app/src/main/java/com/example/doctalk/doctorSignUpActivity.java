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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class doctorSignUpActivity extends AppCompatActivity {

    DatabaseReference mdb;
    private FirebaseAuth Auth;
    FirebaseUser firebaseUser1;
    private EditText UserName,Password,Email ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);
        Spinner spinner=findViewById(R.id.spinner);
        ArrayAdapter<String>myAdapter=new ArrayAdapter<String>(doctorSignUpActivity.this,android.R.layout.simple_list_item_1
                ,getResources().getStringArray(R.array.state));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
        Email = findViewById( R.id.docSignupEmail );
        Password = findViewById( R.id.docSignupPass);
        mdb = FirebaseDatabase.getInstance().getReference();
        Auth = FirebaseAuth.getInstance();
        firebaseUser1 = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser1!=null){
            String a=firebaseUser1.getUid();
            Toast.makeText( doctorSignUpActivity.this,a,Toast.LENGTH_SHORT ).show();
            Intent intent = new Intent( doctorSignUpActivity.this,docChat.class);
            startActivity( intent );
            finish();
        }
    }

    public void SignUp(View view) {
        Auth = FirebaseAuth.getInstance();
        Auth.createUserWithEmailAndPassword( Email.getText().toString(),Password.getText().toString() ).addOnSuccessListener( new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = authResult.getUser();
                final String userId= user.getUid();
                DatabaseReference reference = mdb.child( "doctor" ).child( userId );
                EditText docSignUpfullname,docSignUpMedicalRegistration,docSignupphone;
                Spinner spinner= findViewById(R.id.spinner);
                docSignUpfullname = findViewById(R.id.docSignupFullName);
                docSignUpMedicalRegistration = findViewById(R.id.docSignupMedicalRegistration);
                docSignupphone = findViewById(R.id.docSignupPhone);
                if(userId=="" || Email.getText().toString()=="" || Password.getText().toString()=="" || docSignUpfullname.getText().toString()=="" || docSignUpMedicalRegistration.getText().toString()=="" || spinner.getSelectedItem().toString()==spinner.getItemAtPosition(0)){
                    Toast.makeText( doctorSignUpActivity.this,"Enter All the mandatory credentails",Toast.LENGTH_LONG ).show();
                    return;
                }
                HashMap<String,String> hashMap = new HashMap<>(  );
                hashMap.put("id",userId);
                hashMap.put("Email",Email.getText().toString());
                hashMap.put("PassWord",Password.getText().toString());
                hashMap.put("Name",docSignUpfullname.getText().toString());
                hashMap.put("MedicalId",docSignUpMedicalRegistration.getText().toString());
                hashMap.put("Phone",docSignupphone.getText().toString());
                hashMap.put("City",spinner.getSelectedItem().toString());

                reference.setValue(hashMap).addOnSuccessListener( new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess( Void aVoid ) {
                        Intent intent = new Intent( doctorSignUpActivity.this,docChat.class );
                        intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK );
                        startActivity(intent);
                        finish();
                    }
                } ).addOnFailureListener( new OnFailureListener() {@Override
                    public void onFailure(@NonNull Exception e){}});
            }
        } ).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure( @NonNull Exception e ) {}
        } );
    }
}
