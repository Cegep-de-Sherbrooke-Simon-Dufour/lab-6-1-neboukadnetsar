package com.example.lab.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UsersRepository {

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

    public String getNameByEmail(String emailId) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> task = () -> usersDao.findNameByEmail(emailId);
        Future<String> future = executor.submit(task);

        String emailToReturn;
        try {
            emailToReturn = future.get();
        } catch (InterruptedException | ExecutionException e) {
            emailToReturn = null;
        }

        executor.shutdown();

        return emailToReturn;
    }

    public void deleteUserByEmail(String email) {
        Executors.newSingleThreadExecutor().execute(() -> {
            usersDao.deleteUserByEmail(email);
        });
    }
}
