package com.example.vinhsang.vivmall.domain;

import java.io.Serializable;

/**
 * Created by Long on 3/15/2016.
 */
@SuppressWarnings("serial")
public class ItemProduct implements Serializable {

    protected String product_id;
    protected String product_price;
    protected String product_name;
    protected String product_image;
    protected String product_des;
    protected String more_information;
    protected int product_amount = 1;



    ///contructor
    public ItemProduct(String product_id, String product_price, String product_name, String product_image) {
        this.product_id = product_id;
        this.product_price = product_price;
        this.product_name = product_name;
        this.product_image = product_image;

    }
    public ItemProduct(String product_id) {
        this.product_id = product_id;
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
    public int getProduct_amount() {
        return product_amount;
    }

    public void setProduct_amount(int product_amount) {
        this.product_amount = product_amount;
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


}
