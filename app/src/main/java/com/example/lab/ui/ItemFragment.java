package com.example.lab.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ItemFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UsersListViewModel viewModel  = new ViewModelProvider(requireActivity()).get(UsersListViewModel.class);
        ItemAdapter itemAdapter = new ItemAdapter();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(itemAdapter);
        Button ajouterItem = view.findViewById(R.id.ajouter_item);
        Button supprimerUser = view.findViewById(R.id.supprimer_user);
        EditText ajoutItem_edit = view.findViewById(R.id.edit_ajouter_item);
        TextView name_textView = view.findViewById(R.id.item_user_name);
        TextView emailId_textView = view.findViewById(R.id.item_user_email);
        Bundle array = getArguments();
        String emailId = array.getString("emailId");

        viewModel.getItems(emailId).observe(getViewLifecycleOwner(), items -> {
            itemAdapter.submitList(items);
        });

        ajouterItem.setOnClickListener(v -> {
            viewModel.addItem(ajoutItem_edit.getText().toString(), emailId);
        });

        itemAdapter.callback = (item) -> {
            viewModel.deleteItem(item);
        };

        name_textView.setText(viewModel.getNameByEmail(emailId));
        emailId_textView.setText(emailId);

        supprimerUser.setOnClickListener(v -> {
            viewModel.deleteUserByEmail(emailId);
            Navigation.findNavController(view).navigateUp();
        });
    }
}