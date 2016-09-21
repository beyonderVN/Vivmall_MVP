package com.vinhsang.vivmall.presentation.ui.activity.base;

/**
 * Created by Long on 7/15/2016.
 */

public interface OneInterfaceForAll {

    void registerCallback(OneInterfaceForAll callbacks);
    void unregisterCallback(OneInterfaceForAll callbacks);
    void update(Object work, Object object);

}