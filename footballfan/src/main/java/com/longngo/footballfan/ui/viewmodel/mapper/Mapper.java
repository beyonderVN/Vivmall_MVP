package com.longngo.footballfan.ui.viewmodel.mapper;

import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.ui.viewmodel.CompetitionVM;
import com.longngo.footballfan.ui.viewmodel.vmfactory.Visitable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 10/5/2016.
 */

public class Mapper {
    public static Visitable tran(Competition competition){
        return new CompetitionVM(competition);
    };

    public static List<Visitable> tran(List<Competition> competitions){
        List<Visitable> list = new ArrayList<>();
        for (Competition item :competitions) {
            list.add(tran(item));
        }
        return list;
    };
}
