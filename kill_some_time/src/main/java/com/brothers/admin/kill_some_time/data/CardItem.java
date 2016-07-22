package com.brothers.admin.kill_some_time.data;

/**
 * Created by Admin on 16/07/2016.
 */

public abstract class CardItem {
    private String title;
    private String urlImg;

    public CardItem(String title, String urlImg, String urlImgPlay, String href) {
        this.title = title;
        this.urlImg = urlImg;
        this.urlImgPlay = urlImgPlay;
        this.href = href;
    }

    private String urlImgPlay;
    private String href;

    public CardItem(String title, String urlImg, String href) {
        this.title = title;
        this.urlImg = urlImg;
        this.href = href;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public CardItem() {

    }

    public CardItem(String title, String urlImg) {

        this.title = title;
        this.urlImg = urlImg;
    }

    public String getUrlImgPlay() {
        return urlImgPlay;
    }

    public void setUrlImgPlay(String urlImgPlay) {
        this.urlImgPlay = urlImgPlay;
    }
}
