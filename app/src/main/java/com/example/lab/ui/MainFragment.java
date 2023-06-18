package com.example.lab.ui;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lab.R;
import com.example.lab.data.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UsersListViewModel viewModel  = new ViewModelProvider(requireActivity()).get(UsersListViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_recycler);
        FloatingActionButton ajout = view.findViewById(R.id.fragmentFloatingActionButton);

        CustomAdapter adapter = new CustomAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext())); //getcontext
        recyclerView.setAdapter(adapter);

        /*viewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> users) {
                adapter.submitList(users);
            }
        });*/

        viewModel.getUsers().observe(getViewLifecycleOwner(), users -> {
            adapter.submitList(users);
        });

        adapter.callback = (user) -> {
            viewModel.deleteUser(user);
        };

        /*NavController navController = NavHostFragment.findNavController(this);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_mainFragment_to_actionFragment);
            }
        });*/
        NavController navController = NavHostFragment.findNavController(this);
        ajout.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainFragment_to_actionFragment);
        });
    }
}