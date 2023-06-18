package com.example.lab.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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

import com.example.lab.R;
import com.example.lab.data.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ActionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_action, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Ajouter un utilisateur");
        }

        UsersListViewModel viewModel  = new ViewModelProvider(requireActivity()).get(UsersListViewModel.class);

        Button ajouter = view.findViewById(R.id.ajouter);
        Button annuler = view.findViewById(R.id.annuler);

        ajouter.setOnClickListener(v -> {
            EditText editName = view.findViewById(R.id.editName);
            EditText editCourriel = view.findViewById(R.id.editCourriel);
            viewModel.addUser(editName.getText().toString(), editCourriel.getText().toString());
            Navigation.findNavController(view).navigateUp();
        });

        annuler.setOnClickListener(v -> {
            Navigation.findNavController(view).navigateUp();
        });
    }
}