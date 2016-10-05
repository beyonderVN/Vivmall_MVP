package com.longngo.footballfan.ui.viewmodel.vmfactory;

import android.view.View;

import com.longngo.footballfan.R;
import com.longngo.footballfan.ui.adapter.viewholder.BaseViewHolder;
import com.longngo.footballfan.ui.adapter.viewholder.CompetitionViewHolder;
import com.longngo.footballfan.ui.viewmodel.CompetitionVM;

/**
 * Created by Long on 10/5/2016.
 */

public class TypeFactoryForListVM implements VMTypeFactory {

    @Override
    public int getType(CompetitionVM competitionVM) {
        return R.layout.layout_item_competition;
    }

    @Override
    public BaseViewHolder createHolder(int type, View view) {
        switch(type) {
            case R.layout.layout_item_competition :
                return new CompetitionViewHolder(view);

        }
        return null;
    }
}
