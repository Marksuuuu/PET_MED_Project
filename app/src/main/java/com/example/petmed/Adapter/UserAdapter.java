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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.Fullname.setText(user.getFullname());
//        if (user.getImageUrl().equals("default")){
//            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
//        }else{
//            Glide.with(context).load(user.getImageUrl()).into(holder.profile_image);
//        }

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
        public TextView Fullname;
        public ImageView profile_image;

        public ViewHolder(View itemView) {
            super(itemView);

            Fullname= itemView.findViewById(R.id.fullname);
            profile_image = itemView.findViewById(R.id.profile_image);

        }
    }

}
