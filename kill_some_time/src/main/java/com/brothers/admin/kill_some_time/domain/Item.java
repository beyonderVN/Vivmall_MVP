package com.brothers.admin.kill_some_time.domain;

import java.io.Serializable;

/**
 * Created by Long on 3/15/2016.
 */
@SuppressWarnings("serial")
public class Item implements Serializable {
    private int id;
    private String title;
    private String urlImg;

    public Item() {
    }

    public Item(int id, String title, String urlImg) {
        this.id = id;
        this.title = title;
        this.urlImg = urlImg;
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
}
