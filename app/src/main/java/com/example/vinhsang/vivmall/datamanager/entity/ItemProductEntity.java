package com.example.vinhsang.vivmall.datamanager.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Long on 3/15/2016.
 */
@SuppressWarnings("serial")
public class ItemProductEntity implements Serializable {
    @SerializedName("id")
    protected String product_id;

    @SerializedName("price")
    protected String product_price;

    @SerializedName("name")
    protected String product_name;

    @SerializedName("image")
    protected String product_image;

    @SerializedName("des")
    protected String product_des;

    @SerializedName("info")
    protected String more_information;





    ///contructor
    public ItemProductEntity(String product_id, String product_price, String product_name, String product_image) {
        this.product_id = product_id;
        this.product_price = product_price;
        this.product_name = product_name;
        this.product_image = product_image;

    }
    public ItemProductEntity() {

    }
    public String getProduct_des() {
        return product_des;
    }

    public void setProduct_des(String product_des) {
        this.product_des = product_des;
    }

    ///Getter & Setter
    public String getMore_information() {
        return more_information;
    }

    public void setMore_information(String more_information) {
        this.more_information = more_information;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return "http://vivmall.vn/Nvivmall/upload/product/"+product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }


    @Override public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** User Entity Details *****\n");
        stringBuilder.append("id=" + this.getProduct_id() + "\n");
        stringBuilder.append("cover url=" + this.getProduct_image() + "\n");
        stringBuilder.append("name=" + this.getProduct_name() + "\n");
        stringBuilder.append("description=" + this.getProduct_des() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }

}
