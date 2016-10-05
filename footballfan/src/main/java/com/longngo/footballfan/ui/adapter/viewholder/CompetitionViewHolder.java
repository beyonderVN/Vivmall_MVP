package com.longngo.footballfan.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.longngo.footballfan.R;
import com.longngo.footballfan.ui.viewmodel.CompetitionVM;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 10/5/2016.
 */

public class CompetitionViewHolder extends BaseViewHolder<CompetitionVM> {

    @BindView(R.id.des)
    TextView des;
    public CompetitionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public  void bind(CompetitionVM item) {
        des.setText(item.toString());
    }
}
