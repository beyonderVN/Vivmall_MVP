package com.vinhsang.vivmall.presentation.model;

/**
 * Created by Long on 8/1/2016.
 */

public  abstract class BaseModel {
    public static class ModelType{
        public static final int LOAD_MORE = 0;
        public static final int NO_MORE = 1;
        public static final int ITEM_PRODUCT = 2;
    }

    public abstract int getModelType();
}
