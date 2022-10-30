package com.example.petmed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class connector extends RecyclerView.Adapter<connector.MyViewHolder> {

    Context context;

    ArrayList<productdetails> arr_list;

    public connector(Context context, ArrayList<productdetails> arr_list) {

        this.context = context;
        this.arr_list = arr_list;
    }

    @NonNull
    @Override
    public connector.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.productcard,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull connector.MyViewHolder holder, int position) {
        productdetails productdetails = arr_list.get(position);
        holder.pname.setText(productdetails.getpname());
        holder.description.setText(productdetails.getDescription());
        holder.price.setText(productdetails.getPrice());
    }

    @Override
    public int getItemCount() {
        return arr_list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView pname,description,price;
        public MyViewHolder(@NonNull View itemView){
            super( itemView);

            pname = itemView.findViewById(R.id.pname);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);

        }
    }

}
