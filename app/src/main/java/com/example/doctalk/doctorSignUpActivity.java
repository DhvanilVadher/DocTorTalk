package com.example.doctalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class doctorSignUpActivity extends AppCompatActivity {

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
}
