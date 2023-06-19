package com.example.lab.data;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.Objects;

@Entity(tableName = "users")
public class Users {
    @ColumnInfo
    private String name;
    @PrimaryKey
    @NonNull
    private String email;
    @ColumnInfo(name = "image_uri")
    private String imageUriStringFormat;
    @Ignore
    private Uri imageUriUriFormat;

    public Users(String name, String email, String imageUriStringFormat) {
        this.name = name;
        this.email = email;
        this.imageUriStringFormat = imageUriStringFormat;
        this.imageUriUriFormat = stringToUri(this.imageUriStringFormat);
    }

    @Ignore
    public Users(String name, String email, Uri uri) {
        this.name = name;
        this.email = email;
        this.imageUriUriFormat = uri;
        this.imageUriStringFormat = uriToString(this.imageUriUriFormat);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUriStringFormat() {
        return imageUriStringFormat;
    }

    public Uri getImageUriUriFormat() {
        return imageUriUriFormat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setImageUriStringFormat(String imageUriStringFormat) {
        this.imageUriStringFormat = imageUriStringFormat;
    }

    public static Uri stringToUri(String value) {
        if (value == null) {
            return null;
        }
        return Uri.parse(value);
    }

    public static String uriToString(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }
}
