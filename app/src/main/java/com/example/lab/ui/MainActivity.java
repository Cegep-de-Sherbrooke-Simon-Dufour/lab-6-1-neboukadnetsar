package com.example.lab.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.lab.R;
import com.example.lab.data.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsersListViewModel viewModel  = new ViewModelProvider(this).get(UsersListViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        FloatingActionButton ajout = findViewById(R.id.ajout);

        CustomAdapter adapter = new CustomAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel.getUsers().observe(this, new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> users) {
                adapter.submitList(new ArrayList<>(users));
            }
        });

        adapter.callback = (user) -> {
            viewModel.deleteUser(user);
        };

        ActivityResultLauncher<Intent> ajoutUtilisateur = registerForActivityResult(
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
                            Toast toast = Toast.makeText(MainActivity.this, "Opération annulée !", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
        );

        ajout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AjoutUtilisateur.class);
            ajoutUtilisateur.launch(intent);
        });
    }
}