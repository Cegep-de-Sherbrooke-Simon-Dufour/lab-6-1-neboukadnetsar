package com.example.lab.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class, Item.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {
    public abstract UsersDao getUsersDao();
    public abstract ItemDao getItemsDao();
}
