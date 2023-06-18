package com.example.lab.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsersDao {
    @Query("SELECT * FROM users")
    LiveData<List<Users>> getAllUsers();

    @Query("SELECT * FROM users WHERE email IN (:userEmails)")
    List<Users> loadUsersByEmails(String[] userEmails);

    @Query("SELECT * FROM users WHERE name LIKE :name LIMIT 1")
    Users findByName(String name);

    @Query("SELECT name FROM users WHERE email = :emailId LIMIT 1")
    String findNameByEmail(String emailId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Users... users);

    @Delete
    void delete(Users user);

    @Query("DELETE FROM users WHERE email = :emailId")
    void deleteUserByEmail(String emailId);
}
