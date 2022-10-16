package com.example.petmed.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petmed.MainActivityChat;
import com.example.petmed.R;
import com.example.petmed.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<User> mUsers;

    public UserAdapter (Context context, List<User> mUsers){
        this.mUsers = mUsers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = mUsers.get(position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivityChat.class);
                intent.putExtra("UserID", user.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username1;
        public ImageView profile_image1;

        public ViewHolder(View itemView) {
            super(itemView);

            username1= itemView.findViewById(R.id.username);
            profile_image1 = itemView.findViewById(R.id.profile_image);

        }
    }

}
