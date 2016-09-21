package com.vinhsang.vivmall.presentation.mapper;

import com.vinhsang.vivmall.data.bourbon.model.Shot;
import com.vinhsang.vivmall.presentation.model.BaseModel;
import com.vinhsang.vivmall.presentation.ui.activity.mainactivity.allfragment2.model.ShotModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 9/20/2016.
 */

public class ItemShotMapper {

    public static BaseModel transform(Shot shot) {
        ShotModel itemShotModel = null;
        if (shot != null) {
            itemShotModel = new ShotModel(
                    shot.id,
                    shot.title,
                    shot.description,
                    shot.width,
                    shot.height,
                    shot.images,
                    shot.image,
                    shot.views_count,
                    shot.likes_count,
                    shot.user);
        }

        return itemShotModel;
    }

    public static List<BaseModel> transform(List<Shot> shots){
        List<BaseModel> list = new ArrayList<>();
        for (Shot shot : shots) {

            list.add(transform(shot));


        }
        return list;
    }
}
