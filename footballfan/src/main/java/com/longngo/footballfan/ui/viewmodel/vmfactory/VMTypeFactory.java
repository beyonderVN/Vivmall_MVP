package com.longngo.footballfan.ui.viewmodel.vmfactory;

import android.view.View;

import com.longngo.footballfan.ui.adapter.viewholder.BaseViewHolder;
import com.longngo.footballfan.ui.viewmodel.CompetitionVM;

/**
 * Created by Long on 10/5/2016.
 */

public interface VMTypeFactory {
    int getType(CompetitionVM competitionVM);

    BaseViewHolder createHolder(int type, View view);
}
