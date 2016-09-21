package com.vinhsang.vivmall.presentation.ui.activity.main.allfragment2.model;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserModel implements Serializable {

    public int id;
    public String name;
    public String username;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("avatar_url")
    public String avatarUrl;



    public UserModel() {
    }

    public UserModel(int id, String name, String username, String htmlUrl, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.htmlUrl = htmlUrl;
        this.avatarUrl = avatarUrl;
    }

    protected UserModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.username = in.readString();
        this.htmlUrl = in.readString();
        this.avatarUrl = in.readString();
    }


}
