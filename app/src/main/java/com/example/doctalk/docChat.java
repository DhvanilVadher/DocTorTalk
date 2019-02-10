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

import static com.example.doctalk.universal.doc_or_not;

public class docChat extends AppCompatActivity {
    RecyclerView recycler1;
    DocMessageAdapter adapter;
    final ArrayList<Chat>Chats = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_chat);
        receieveMessage();
    }


    //logout

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(docChat.this,MainActivity.class );
        startActivity( intent );
        finish();
    }

    //Function to send Message

    public void SendTo(View view) {
        EditText text = findViewById(R.id.editText);
        final String FinalMessage = text.getText().toString();
        final FirebaseUser  myUser= FirebaseAuth.getInstance().getCurrentUser();
        final HashMap<String,Object>hashMap = new HashMap<>();
        hashMap.put("message",FinalMessage);

        if(doc_or_not==true)
            hashMap.put("name","Doctor");
        else
            hashMap.put("name","Patient");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Messages").push().setValue(hashMap);
        text.setText("");
    }

    //Function to receievemessage from database

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
                    Chats.add(chat);
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
