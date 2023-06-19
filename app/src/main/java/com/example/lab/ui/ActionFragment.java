package com.example.lab.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
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
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.lab.R;
import com.example.lab.data.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActionFragment extends Fragment {

    private ImageView photoProfil;
    private File folder;
    private File file;
    private Uri uri;
    private ActivityResultLauncher<Uri> cameraResultLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_action, container, false);

        folder = new File(requireContext().getFilesDir(), "camera_images");
        if (!folder.exists()) folder.mkdirs();

        file = new File(folder, UUID.randomUUID().toString() + ".png");

        uri = FileProvider.getUriForFile(requireContext(), requireContext().getPackageName() + ".provider", file);

        photoProfil = view.findViewById(R.id.imageView_profil_ajouter);

        if (!folder.exists()) folder.mkdirs();
        File file = new File(folder, UUID.randomUUID().toString() + ".png");
        cameraResultLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                pictureTaken -> {
                    if (pictureTaken) {
                        photoProfil.setImageURI(uri);
                    }
                }
        );
        return view;
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

        ImageButton prendrePhoto = view.findViewById((R.id.imageButton_take));
        ImageButton galerie = view.findViewById((R.id.imageButton_gallery));

        ActivityResultLauncher<PickVisualMediaRequest> takeGallery = registerForActivityResult(
                new ActivityResultContracts.PickVisualMedia(),
                uriPhoto -> {
                    photoProfil.setImageURI(uriPhoto);
                    uri = uriPhoto;
                }
        );

        galerie.setOnClickListener(v -> {
            takeGallery.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());
        });

        prendrePhoto.setOnClickListener(v -> {
            cameraResultLauncher.launch(uri);
        });

        ajouter.setOnClickListener(v -> {
            EditText editName = view.findViewById(R.id.editName);
            EditText editCourriel = view.findViewById(R.id.editCourriel);
            viewModel.addUser(editName.getText().toString(), editCourriel.getText().toString(), uri);
            Navigation.findNavController(view).navigateUp();
        });

        annuler.setOnClickListener(v -> {
            Navigation.findNavController(view).navigateUp();
        });
    }
}