package com.petestapp.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petestapp.app.R;

public class KeyBoardAdapter extends RecyclerView.Adapter<KeyBoardAdapter.KeyHolder> {

    private KeyBoardContract contract;

    public KeyBoardAdapter(KeyBoardContract contract) {
        this.contract = contract;
    }

    @NonNull
    @Override
    public KeyHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new KeyHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_keys, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull  KeyBoardAdapter.KeyHolder holder, int position) {
        holder.tvChar.setText(contract != null ? contract.getChar(position) : "");
        holder.tvChar.setOnClickListener(v -> {
            if (contract != null){
                contract.onKeyClicker(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return contract == null ? 0 : contract.getLength();
    }

    public static class KeyHolder extends RecyclerView.ViewHolder{
        TextView tvChar;
        public KeyHolder(@NonNull View itemView) {
            super(itemView);
            tvChar = itemView.findViewById(R.id.tvChar);
        }
    }

    public interface KeyBoardContract{
        int getLength();
        String getChar(int position);
        void onKeyClicker(int position);
    }
}
