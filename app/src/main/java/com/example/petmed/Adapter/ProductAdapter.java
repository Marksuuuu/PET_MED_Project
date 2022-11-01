package com.example.petmed.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.petmed.R;
import com.example.petmed.model.Prod;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    ArrayList<Prod> prods;

    public ProductAdapter(Context context, ArrayList<Prod> prods) {
        this.context = context;
        this.prods = prods;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.productcard,parent,false);
        return  new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Prod p = prods.get(position);
        String url = p.getImageURL();
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.getimg);
        Prod prod = prods.get(position);
        holder.name.setText(prod.getName());
        holder.description.setText(prod.getDescription());
        holder.price_here.setText(prod.getPrice());
    }

    @Override
    public int getItemCount() {
        return prods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name , description , price_here;
        ImageView getimg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            getimg = itemView.findViewById(R.id.ivProduct);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            price_here = itemView.findViewById(R.id.price);
            getimg = itemView.findViewById(R.id.getimg);
        }
    }
}
