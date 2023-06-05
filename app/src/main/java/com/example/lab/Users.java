package com.example.lab;

import java.util.Objects;

public class Users {
    private String name;
    private String email;

    public Users(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return name.equals(users.name) && email.equals(users.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
