package com.jsoftware.capstone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MedicAdapter extends RecyclerView.Adapter<MedicAdapter.ViewHolder>{

    private Context context;
    private List<Medic> MedicList;
    private MedicAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public MedicAdapter(Context context, List<Medic> MedicList) {
        this.context = context;
        this.MedicList = MedicList;
    }

    @NonNull
    @Override
    public MedicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_medic, viewGroup, false);
        return new MedicAdapter.ViewHolder(view, mListener);
    }

    public void setOnItemClickListener(MedicAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicAdapter.ViewHolder viewHolder, int i) {
        Medic medic = MedicList.get(i);

        viewHolder.medicName.setText(medic.getMedicName());
        viewHolder.medicNumber.setText(medic.getMedicNumber());
        viewHolder.medicAddress.setText(medic.getMedicAddress());
        viewHolder.medicExperience.setText(medic.getMedicExperience());
        viewHolder.medicQualification.setText(medic.getMedicQualification());

    }

    @Override
    public int getItemCount() {
        return MedicList.size();
    }

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView medicName, medicNumber, medicAddress, medicExperience, medicQualification;

    public ViewHolder(@NonNull final View itemView, final MedicAdapter.OnItemClickListener listener) {
        super(itemView);
        medicName = itemView.findViewById(R.id.medic_name);
        medicNumber = itemView.findViewById(R.id.medic_number);
        medicAddress = itemView.findViewById(R.id.medic_address);
        medicExperience = itemView.findViewById(R.id.medic_experience);
        medicQualification = itemView.findViewById(R.id.medic_qualification);

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