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
import com.example.lab.RecyclerCallback;
import com.example.lab.data.Users;

public class CustomAdapter extends ListAdapter<Users, CustomAdapter.ViewHolder> {

    public RecyclerCallback<Users> callback = (U) -> {};

    public CustomAdapter() {
        super(new DiffUtil.ItemCallback<Users>() {
            @Override
            public boolean areItemsTheSame(Users oldUser, Users newUser) {
                return oldUser == newUser;
            }

            @Override
            public boolean areContentsTheSame(Users oldUser, Users newUser) {
                return oldUser.getEmail().equals(newUser.getEmail()) && oldUser.getName().equals(newUser.getName());
            }
        });
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItems(getItem(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, email;
        Users user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            itemView.setOnClickListener(view -> {
                callback.returnValue(user);
            });
        }

        public void setItems(Users user) {
            this.user = user;
            this.name.setText(user.getName());
            this.email.setText(user.getEmail());
        }


    }
}
