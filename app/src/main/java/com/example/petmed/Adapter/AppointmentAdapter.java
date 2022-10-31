package com.example.petmed.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petmed.R;
import com.example.petmed.model.App;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
    Context context;

    ArrayList<App> list;

    public AppointmentAdapter(Context context, ArrayList<App> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.ViewHolder holder, int position) {
        App app = list.get(position);
        holder.symptoms.setText(app.getSymptoms());
        holder.name.setText(app.getName());
        holder.time.setText(app.getTime());
        holder.type.setText(app.getType());
        holder.reason.setText(app.getReason());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name , reason , symptoms , date , time , type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            reason = itemView.findViewById(R.id.reason);
            symptoms = itemView.findViewById(R.id.symptoms);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time_hr);
            type = itemView.findViewById(R.id.type);
        }
    }
}
