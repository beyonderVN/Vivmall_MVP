package com.vinhsang.vivmall.domain;

import java.io.Serializable;

/**
 * Created by Long on 7/25/2016.
 */

public class Catalogue implements Serializable{
    String title ;
    int id;

    public Catalogue(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public Catalogue() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Entity Details *****\n");
        stringBuilder.append("id=" + this.getId() + "\n");
        stringBuilder.append("title title=" + this.getTitle() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
