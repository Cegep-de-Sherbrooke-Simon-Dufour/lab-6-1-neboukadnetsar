package com.example.lab.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UsersRepository {

    @Inject
    public UsersRepository() {}

    private ArrayList<Users> users = new ArrayList<>(Arrays.asList(
            new Users("Gab", "gab@gmail.com"),
            new Users("Mat", "mat@gmail.com"),
            new Users("Max", "max@gmail.com"),
            new Users("Dundee", "dundee@gmail.com")
    ));
    private final MutableLiveData<List<Users>> usersLiveData = new MutableLiveData<>(users);

    public void addUser(Users user) {
        users.add(user);
        usersLiveData.setValue(users);
    }

    public void deleteUser(Users user) {
        users.remove(user);
        usersLiveData.setValue(users);
    }

    public LiveData<List<Users>> getLiveDataUsers() {
        return usersLiveData;
    }
}
