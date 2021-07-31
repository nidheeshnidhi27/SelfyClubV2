package com.selfyclub.selfyclubappV2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selfyclub.selfyclubappV2.R;

import java.util.ArrayList;

public class Location_Filter_Adapter extends RecyclerView.Adapter<Location_Filter_Adapter.ViewHolder> {

    Context mContext;
    ArrayList<String> locs = null;

    public Location_Filter_Adapter(Context context, ArrayList<String> locs) {

        this.mContext = context;
        this.locs = locs;
//        this.data = data;


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_location_filter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.RBLocation.setText(locs.get(position));

        /*holder.RBLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton checked_rb = (RadioButton) v;
                if(holder.RBLocation != null){
                    holder.RBLocation.setChecked(false);
                }
                holder.RBLocation = checked_rb;
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return locs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton RBLocation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            RBLocation = itemView.findViewById(R.id.radioButton2);

        }
    }
}
