package com.example.doctalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class doctorSignUpActivity extends AppCompatActivity {
    DatabaseReference mfb;
    FirebaseUser Auth;
    FirebaseUser firebaseUser;
    EditText email,password,fullname,madicalNumber;
    String Email,Password,Fullname,MedicalNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);
        Spinner spinner=findViewById(R.id.spinner);
        ArrayAdapter<String>myAdapter=new ArrayAdapter<String>(doctorSignUpActivity.this,android.R.layout.simple_list_item_1
                ,getResources().getStringArray(R.array.state));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
    }

    public void SignUp(View view) {email = findViewById(R.id.docSignupEmail);
        mfb  = FirebaseDatabase.getInstance().getReference();
        password = findViewById(R.id.docSignupPass);
        fullname = findViewById(R.id.docSignupFullName);
        madicalNumber  =findViewById(R.id.docSignupMedicalRegistration);
        Email  = email.getText().toString();
        Password = password.getText().toString();
        Fullname = fullname.getText().toString();
        MedicalNumber = madicalNumber.getText().toString();
        HashMap<String,String>  hashMap = new HashMap<>();
        hashMap.clear();
        hashMap.put("Email",Email);
        hashMap.put("Password",Password);
        hashMap.put("FullName",Fullname);
        hashMap.put("Medical",MedicalNumber);
        DatabaseReference ref=mfb.child("doctor");
        ref.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(doctorSignUpActivity.this,docChat.class);
                startActivity(intent);
            }
        });
    }
}
