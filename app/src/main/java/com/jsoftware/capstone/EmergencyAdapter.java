package com.jsoftware.capstone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.ViewHolder>{

    private Context context;
    private List<Emergency> emergencyList;
    private EmergencyAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public EmergencyAdapter(Context context, List<Emergency> emergencyList) {
        this.context = context;
        this.emergencyList = emergencyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_emergency, viewGroup, false);
        return new ViewHolder(view, mListener);
    }

    public void setOnItemClickListener(EmergencyAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Emergency emergency = emergencyList.get(i);
        viewHolder.location.setText(emergency.getEmergencyLocation());
    }

    @Override
    public int getItemCount() {
        return emergencyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView location;

        public ViewHolder(@NonNull final View itemView, final EmergencyAdapter.OnItemClickListener listener) {
            super(itemView);
            location = itemView.findViewById(R.id.location_txtView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
