package com.example.lab.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM items WHERE emailId = :emailId")
    LiveData<List<Item>> getAllItemsForOneUser(String emailId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item... item);

    @Delete
    void delete(Item item);
}
