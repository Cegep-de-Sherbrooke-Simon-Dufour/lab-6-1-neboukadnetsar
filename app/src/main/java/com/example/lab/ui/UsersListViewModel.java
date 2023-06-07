package com.example.lab.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab.data.Users;
import com.example.lab.data.UsersRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UsersListViewModel extends ViewModel {

    private UsersRepository repository;

    @Inject
    public UsersListViewModel(UsersRepository repository) {
        this.repository = repository;
    }

    public void addUser(String name, String courriel) {
        repository.addUser(new Users(name, courriel));
    }

    public void deleteUser(Users user) {
        repository.deleteUser(user);
    }

    public LiveData<List<Users>> getUsers() {
        return repository.getLiveDataUsers();
    }
}
