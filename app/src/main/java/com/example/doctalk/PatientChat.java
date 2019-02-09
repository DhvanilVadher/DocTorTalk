package com.example.doctalk;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
public class PatientChat extends AppCompatActivity {
    private RecyclerView recycler1;
    private DocMessageAdapter adapter;
    private final ArrayList<Chat> Chats = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_chat);
        receieveMessage();
    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(PatientChat.this,MainActivity.class );
        startActivity( intent );
        finish();
    }
    public void SendTo(View view) {
        EditText text = findViewById(R.id.editText);
        String FinalMessage = text.getText().toString();
        FirebaseUser myUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("sender",myUser.getUid().toString());
        hashMap.put("sender_is_doctor",false);
        hashMap.put("Message",FinalMessage);
        ref.child("Messages").push().setValue(hashMap);
        text.setText("");
    }
    private void receieveMessage()
    {
        DatabaseReference dbrr = FirebaseDatabase.getInstance().getReference("Messages");
        dbrr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Chats.clear();
                for(DataSnapshot snp:dataSnapshot.getChildren()) {
                    Chat chat = snp.getValue(Chat.class);
                    if (chat == null) {
                        Log.v("Hello", "Please DO Something");
                        continue;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        recycler1 = (RecyclerView)findViewById(R.id.myView);
        if(recycler1==null)
        {
            Log.v("dadhk","aaaaa");
        }
        recycler1.setLayoutManager(new LinearLayoutManager(this));
        recycler1.setHasFixedSize(true);
        adapter=new DocMessageAdapter(this,Chats);
        recycler1.setAdapter(adapter);
    }
}
