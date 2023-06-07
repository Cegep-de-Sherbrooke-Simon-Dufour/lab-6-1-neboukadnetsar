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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setContentView(R.layout.activity_main);

        UsersListViewModel viewModel  = new ViewModelProvider(requireActivity()).get(UsersListViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_recycler);
        FloatingActionButton ajout = view.findViewById(R.id.fragmentFloatingActionButton);

        CustomAdapter adapter = new CustomAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext())); //getcontext
        recyclerView.setAdapter(adapter);

        viewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> users) {
                adapter.submitList(new ArrayList<>(users));
            }
        });

        adapter.callback = (user) -> {
            viewModel.deleteUser(user);
        };

        /*ActivityResultLauncher<Intent> ajoutUtilisateur = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        if (result.getResultCode() == RESULT_OK) {
                            String nom = data.getStringExtra("nom");
                            String courriel = data.getStringExtra("courriel");
                            viewModel.addUser(nom, courriel);
                        } else if(result.getResultCode() == RESULT_CANCELED) {
                            Toast toast = Toast.makeText(requireContext(), "Opération annulée !", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
        );

        ajout.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), AjoutUtilisateur.class);
            ajoutUtilisateur.launch(intent);
        });*/

        NavController navController = NavHostFragment.findNavController(this);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Appeler l'action de navigation définie dans le graphique de navigation
                navController.navigate(R.id.action_mainFragment_to_actionFragment);
            }
        });

        /*Bundle args = getArguments();
        if (args != null) {
            String nom = args.getString("nom");
            String courriel = args.getString("courriel");

            *//*String nom = data.getStringExtra("nom");
            String courriel = data.getStringExtra("courriel");*//*
            viewModel.addUser(nom, courriel);
            // Utilisez les données ici
        }*/
    }
}