package com.example.lab.data;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemRepository {
    //private final LiveData<List<Item>> itemsLiveData;
    private final ItemDao itemDao;

    @Inject
    public ItemRepository(UsersDatabase database) {
        itemDao = database.getItemsDao();
    }

    public void addItem(Item item) {
        Executors.newSingleThreadExecutor().execute(() -> {
            itemDao.insert(item);
        });
    }

    public void deleteItem(Item item) {
        Executors.newSingleThreadExecutor().execute(() -> {
            itemDao.delete(item);
        });
    }

    public LiveData<List<Item>> getLiveDataItems(String email) {
        return itemDao.getAllItemsForOneUser(email);
    }
}
