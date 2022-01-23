package com.project.danielbachelor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.danielbachelor.datenbank.entitaet.hardwaresystem;
import com.project.danielbachelor.datenbank.entitaet.standort;

import java.util.List;

public class HWSRecAdapter extends RecyclerView.Adapter<HWSRecAdapter.ViewHolder>{
    private List<standort> mHWS;

    public HWSRecAdapter(List<standort> HWS){
        this.mHWS = HWS;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_hws_daten, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getBGradTextView().setText(Double.toString(mHWS.get(position).getSBreitengrad()));
        holder.getLGradTextView().setText(Double.toString(mHWS.get(position).getSLaengengrad()));
        holder.getDateTimeTextView().setText(mHWS.get(position).getSErfassungszeit().toString());
        holder.getHWSNameTextView().setText(mHWS.get(position).getHName());
    }

    @Override
    public int getItemCount() {
        return mHWS.size();
    }

    public List<standort> getmHWS() {
        return mHWS;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView HWSNameTextView;
        private final TextView DateTimeTextView;
        private final TextView BGradTextView;
        private final TextView LGradTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            HWSNameTextView = (TextView) itemView.findViewById(R.id.HWSNameTextView);
            DateTimeTextView = (TextView) itemView.findViewById(R.id.DateTimeTextView);
            BGradTextView = (TextView) itemView.findViewById(R.id.BGradTextView);
            LGradTextView = (TextView) itemView.findViewById(R.id.LGradTextView);

        }

        public TextView getHWSNameTextView() {
            return HWSNameTextView;
        }

        public TextView getDateTimeTextView() {
            return DateTimeTextView;
        }

        public TextView getBGradTextView() {
            return BGradTextView;
        }

        public TextView getLGradTextView() {
            return LGradTextView;
        }
    }
}
