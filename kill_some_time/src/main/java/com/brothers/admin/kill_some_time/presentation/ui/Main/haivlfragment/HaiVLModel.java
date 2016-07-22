package com.brothers.admin.kill_some_time.presentation.ui.Main.haivlfragment;

import com.brothers.admin.kill_some_time.domain.Item;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Long on 7/22/2016.
 */

public class HaiVLModel implements Serializable {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
