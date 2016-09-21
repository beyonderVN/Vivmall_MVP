package com.vinhsang.vivmall.presentation.ui.activity.main.allfragment2.model;

import java.io.Serializable;

public class ImageModel implements Serializable {
    public String hidpi;
    public String normal;
    public String teaser;



    public ImageModel() {
    }

    public String getImage() {
        return hidpi != null ? hidpi : normal;
    }

    public ImageModel(String hidpi, String normal, String teaser) {
        this.hidpi = hidpi;
        this.normal = normal;
        this.teaser = teaser;
    }
}
