package com.vinhsang.vivmall.presentation.model;

/**
 * Created by Long on 8/1/2016.
 */

public  abstract class BaseModel {
    public static class ModelType{
        public static final int ITEM_PRODUCT = 1;
        public static final int ITEM_PRODUCT2 = 2;
        public static final int ITEM_SHOT = 4;
        public static final int SECTION = 3;
    }

    public abstract int getModelType();
}
