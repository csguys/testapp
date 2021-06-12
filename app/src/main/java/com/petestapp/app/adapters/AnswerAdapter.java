package com.petestapp.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petestapp.app.R;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerHolder>{

    private AnswerContract contract;

    public AnswerAdapter(AnswerContract contract) {
        this.contract = contract;
    }

    @NonNull
    @Override
    public AnswerHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new AnswerHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_keys, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull  AnswerAdapter.AnswerHolder holder, int position) {
        holder.tvChar.setText(contract != null ? contract.getAnswerChar(position) : "");
    }

    @Override
    public int getItemCount() {
        return contract == null ? 0 : contract.getAnswerLength();
    }

    public static class AnswerHolder extends RecyclerView.ViewHolder{
        TextView tvChar;
        public AnswerHolder(@NonNull View itemView) {
            super(itemView);
            tvChar = itemView.findViewById(R.id.tvChar);
        }
    }

    public interface AnswerContract{
        int getAnswerLength();
        String getAnswerChar(int position);
    }
}
