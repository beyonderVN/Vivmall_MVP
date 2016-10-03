package com.vinhsang.vivmall.presentation.ui.activity.main.allfragment2.model;


import com.vinhsang.vivmall.data.dribbble.model.Image;
import com.vinhsang.vivmall.data.dribbble.model.User;
import com.vinhsang.vivmall.presentation.model.BaseModel;

import java.io.Serializable;

public class ShotModel extends BaseModel implements Serializable {

    public int id;
    public String title;
    public String description;
    public int width;
    public int height;
    public ImageModel images;
    public String image;
    public String views_count;
    public String likes_count;
    public UserModel userModel;


    protected int type;

    public ShotModel() {
        type= ModelType.ITEM_SHOT;
    }

    public ShotModel(int id, String title, String description, int width, int height, Image images, String image, String views_count, String likes_count, User user) {
        this();
        this.id = id;
        this.title = title;
        this.description = description;
        this.width = width;
        this.height = height;
        this.images = new ImageModel(images.hidpi,images.normal,images.teaser);
        this.image = image;
        this.views_count = views_count;
        this.likes_count = likes_count;
        this.userModel = new UserModel(user.id,user.name,user.username,user.htmlUrl,user.avatarUrl);
    }

    @Override
    public int getModelType() {
        return getType();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ImageModel getImages() {
        return images;
    }

    public void setImages(ImageModel images) {
        this.images = images;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getViews_count() {
        return views_count;
    }

    public void setViews_count(String views_count) {
        this.views_count = views_count;
    }

    public String getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(String likes_count) {
        this.likes_count = likes_count;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
