package com.example.lab;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //private String _nom = "";
    //private String _courriel = "";
    private ArrayList<Users> users = new ArrayList<>(Arrays.asList(
            new Users("Gab", "gab@gmail.com"),
            new Users("Mat", "mat@gmail.com"),
            new Users("Max", "max@gmail.com"),
            new Users("Dundee", "dundee@gmail.com")
    ));
    //private CustomAdapter adapter = new CustomAdapter();
    private CustomAdapter adapter = new CustomAdapter();

    ActivityResultLauncher<Intent> ajoutUtilisateur = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent data = result.getData();
                if (data != null) {
                    String nom = data.getStringExtra("nom");
                    String courriel = data.getStringExtra("courriel");
                    users.add(new Users(nom, courriel));
                    adapter.submitList(new ArrayList<>(users));
                }
                //adapter.notifyDataSetChanged();
                //recyclerView.setAdapter(adapter);
                //System.out.println(nomX);
            }
        }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
        adapter.submitList(new ArrayList<>(users));
        adapter.callback = (user) -> {
            users.remove(user);
            adapter.submitList(new ArrayList<>(users));
        };

        FloatingActionButton ajout = findViewById(R.id.ajout);
        ajout.setOnClickListener(ajoutListener);
    }

    View.OnClickListener ajoutListener = new View.OnClickListener() {
        public void onClick(View v) {
            //Log.i("test", "test");
            Intent intent = new Intent(MainActivity.this, AjoutUtilisateur.class);
            ajoutUtilisateur.launch(intent);
        }
    };
}