package com.example.lab.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "items",
    foreignKeys = {
        @ForeignKey(
                entity = Users.class,
                parentColumns = "email",
                childColumns = "emailId",
                onDelete = ForeignKey.CASCADE
        )
    }
)
public class Item {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;
    private String emailId;

    public Item(String nom, String emailId) {
        this.nom = nom;
        this.emailId = emailId;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setId(int id) {
        this.id = id;
    }

}
