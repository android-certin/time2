package com.ciandt.worldwonders.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by andersonr on 21/08/15.
 */
public class User implements Parcelable {

    private String name;

    private String username;

    private String password;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User(Parcel in) {
        String[] data = new String[3];

        in.readStringArray(data);
        this.name = data[0];
        this.username = data[1];
        this.password = data[2];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name, this.username, this.password});
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
