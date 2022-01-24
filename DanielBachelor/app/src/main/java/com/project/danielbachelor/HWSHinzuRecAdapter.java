package com.project.danielbachelor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.danielbachelor.hwshinzufuegen.hwshinzufuegenKontrakt;

import java.util.List;

public class HWSHinzuRecAdapter extends RecyclerView.Adapter<HWSHinzuRecAdapter.ViewHolder> {
    private List<String> HNameListe;
    private static hwshinzufuegenKontrakt.Presenter mPresenter;

    public HWSHinzuRecAdapter(List<String> HNameListe, hwshinzufuegenKontrakt.Presenter myPresenter) {
        this.HNameListe = HNameListe;
        mPresenter = myPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_hws_name, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HWSHinzuRecAdapter.ViewHolder holder, int position) {
        holder.getHName().setText(HNameListe.get(position));
        holder.setHNameString(HNameListe.get(position));
    }

    @Override
    public int getItemCount() {
        return HNameListe.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView HName;
        private String HNameString;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            HName = itemView.findViewById(R.id.HWSSingleNameTextView);
            HNameString = "";
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenter.oeffneDialogZumVerknuepfen(HNameString);
                }
            });
        }

        public TextView getHName() {
            return HName;
        }

        public void setHNameString(String HNameString) {
            this.HNameString = HNameString;
        }
    }
}
