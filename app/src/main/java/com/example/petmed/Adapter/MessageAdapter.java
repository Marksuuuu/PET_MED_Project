package com.example.petmed.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petmed.MainActivityChat;
import com.example.petmed.R;
import com.example.petmed.model.Chat;
import com.example.petmed.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 0;

    private Context context;
    private List<Chat> mChat;

    FirebaseUser fuser;

    public MessageAdapter (Context context, List<Chat> mChat){
        this.mChat = mChat;
        this.context = context;
    }

    public MessageAdapter(MainActivityChat context, List<User> mChat, CircleImageView profile_image) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat chat = mChat.get(position);

        holder.show_message.setText(position);



    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        User user = mUsers.get(position);
//
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, MainActivityChat.class);
//                intent.putExtra("UserID", user.getId());
//                context.startActivity(intent);
//            }
//        });
//
//    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;


        public ViewHolder(View itemView) {
            super(itemView);

            show_message= itemView.findViewById(R.id.show_message);


        }
    }
    @Override
    public int getItemViewType(int position){
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_LEFT;
        }else {
            return MSG_TYPE_LEFT;
        }
    }

}
