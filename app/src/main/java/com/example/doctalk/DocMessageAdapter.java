package com.example.doctalk;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class DocMessageAdapter extends RecyclerView.Adapter<DocMessageAdapter.ViewHolder1> {
    private Context ctx;
    private ArrayList<Chat> MyChats;

    public DocMessageAdapter(Context ctx, ArrayList<Chat> myChats) {
        this.ctx = ctx;
        MyChats = myChats;
    }
    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View v = LayoutInflater.from(ctx).inflate( R.layout.messagemodeldoctor,parent ,false);
                return new DocMessageAdapter.ViewHolder1( v );
            case 1:
                View v1 = LayoutInflater.from(ctx).inflate( R.layout.messagemodelpatient,parent ,false);
                return new DocMessageAdapter.ViewHolder1( v1 );
            default:return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {
        Chat chat=MyChats.get( position );
        holder.FinamMessage.setText(chat.getMessage());
        holder.Sender.setText(chat.getName());
    }
    @Override
    public int getItemCount() {
        return MyChats.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        public TextView FinamMessage,Sender;
        public ViewHolder1( View itemView ) {
            super(itemView);
            FinamMessage = itemView.findViewById(R.id.FinalMessage);
            Sender = itemView.findViewById(R.id.backname);
        }
    }
}
