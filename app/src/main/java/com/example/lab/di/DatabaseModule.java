package com.example.lab.di;

import android.content.Context;

import androidx.room.Room;

import com.example.lab.data.UsersDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Provides
    public static UsersDatabase provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, UsersDatabase.class, "database")
                .fallbackToDestructiveMigration()
                .build();
    }
}

