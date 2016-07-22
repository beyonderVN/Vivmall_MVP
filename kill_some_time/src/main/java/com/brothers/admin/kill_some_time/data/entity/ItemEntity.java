package com.brothers.admin.kill_some_time.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Long on 3/15/2016.
 */
@SuppressWarnings("serial")
public class ItemEntity implements Serializable {
    @SerializedName("id")
    protected int id;

    @SerializedName("title")
    protected String title;

    @SerializedName("urlImg")
    private String urlImg;

    public ItemEntity(int id, String title, String urlImg) {
        this.id = id;
        this.title = title;
        this.urlImg = urlImg;
    }

    public ItemEntity(int id) {

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    @Override public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Entity Details *****\n");
        stringBuilder.append("id=" + this.getId() + "\n");
        stringBuilder.append("title=" + this.getTitle() + "\n");
        stringBuilder.append("urlImg=" + this.getUrlImg() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }

}
