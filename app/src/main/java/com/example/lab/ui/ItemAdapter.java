package com.example.lab.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab.R;
import com.example.lab.data.Item;
import com.example.lab.data.Users;

public class ItemAdapter extends ListAdapter<Item, ItemAdapter.ViewHolder> {

    public RecyclerIOneParameterCallback<Item> callback = (I) -> {};

    public ItemAdapter() {
        super(new DiffUtil.ItemCallback<Item>() {
            @Override
            public boolean areItemsTheSame(Item oldItem, Item newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(Item oldItem, Item newItem) {
                return oldItem.getNom().equals(newItem.getNom());
            }
        });
    }


    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_individuel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        holder.setItems(getItem(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        Item item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_individuel);
            itemView.setOnClickListener(view -> {
                callback.returnValue(item);
            });
        }

        public void setItems(Item item) {
            this.item = item;
            this.name.setText(item.getNom());
        }
    }
}

