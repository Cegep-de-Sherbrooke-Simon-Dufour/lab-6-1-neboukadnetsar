package com.example.lab.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UsersRepository {

    private ArrayList<Users> users = new ArrayList<>(Arrays.asList(
            new Users("Gab", "gab@gmail.com"),
            new Users("Mat", "mat@gmail.com"),
            new Users("Max", "max@gmail.com"),
            new Users("Dundee", "dundee@gmail.com")
    ));
    //private final MutableLiveData<List<Users>> usersLiveData = new MutableLiveData<>(new ArrayList<>(users));
    private final LiveData<List<Users>> usersLiveData;
    private final UsersDao usersDao;

    @Inject
    public UsersRepository(UsersDatabase database) {
        usersDao = database.getUsersDao();
        usersLiveData = usersDao.getAllUsers();
    }

    public void addUser(Users user) {
        Executors.newSingleThreadExecutor().execute(() -> {
            usersDao.insert(user);
        });
    }

    public void deleteUser(Users user) {
        Executors.newSingleThreadExecutor().execute(() -> {
            usersDao.delete(user);
        });
    }

    public LiveData<List<Users>> getLiveDataUsers() {
        return usersLiveData;
    }
}
