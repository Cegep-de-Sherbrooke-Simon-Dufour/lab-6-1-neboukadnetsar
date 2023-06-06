package com.example.lab.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AjoutUtilisateur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_utilisateur);

        Button ajouter = findViewById(R.id.ajouter);
        //ajouter.setOnClickListener(ajouterListener);
        ajouter.setOnClickListener(v -> {
            EditText editName = findViewById(R.id.editName);
            EditText editCourriel = findViewById(R.id.editCourriel);
            Intent intent = new Intent();
            intent.putExtra("nom", editName.getText().toString());
            intent.putExtra("courriel", editCourriel.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        });

        Button annuler = findViewById(R.id.annuler);
        annuler.setOnClickListener(annulerListener);
    }

    /*View.OnClickListener ajouterListener = new View.OnClickListener() {
        public void onClick(View v) {

            EditText editName = findViewById(R.id.editName);
            EditText editCourriel = findViewById(R.id.editCourriel);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("nom", editName.getText().toString());
            resultIntent.putExtra("courriel", editCourriel.getText().toString());
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    };*/

    View.OnClickListener annulerListener = new View.OnClickListener() {
        public void onClick(View v) {
            setResult(RESULT_CANCELED);
            finish();
        }
    };
}
