package com.example.lab.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab.data.Item;
import com.example.lab.data.ItemRepository;
import com.example.lab.data.Users;
import com.example.lab.data.UsersRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UsersListViewModel extends ViewModel {

    private UsersRepository usersRepository;
    private ItemRepository itemRepository;

    @Inject
    public UsersListViewModel(UsersRepository userRepository, ItemRepository itemRepository) {
        this.usersRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public void addUser(String name, String courriel) {
        usersRepository.addUser(new Users(name, courriel));
    }

    public void deleteUser(Users user) {
        usersRepository.deleteUser(user);
    }

    public LiveData<List<Users>> getUsers() {
        return usersRepository.getLiveDataUsers();
    }

    public void addItem(String name, String emailId) {
        itemRepository.addItem(new Item(name, emailId));
    }

    public void deleteItem(Item item) {
        itemRepository.deleteItem(item);
    }

    public LiveData<List<Item>> getItems() {
        return itemRepository.getLiveDataItems();
    }
}
