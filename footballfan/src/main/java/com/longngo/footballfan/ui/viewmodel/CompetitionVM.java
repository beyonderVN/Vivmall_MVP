package com.longngo.footballfan.ui.viewmodel;

import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.ui.viewmodel.vmfactory.VMTypeFactory;
import com.longngo.footballfan.ui.viewmodel.vmfactory.Visitable;

import java.io.Serializable;

/**
 * Created by Long on 10/5/2016.
 */

public class CompetitionVM implements Serializable,Visitable {

    Competition competition;

    public CompetitionVM(Competition competition) {
        this.competition = competition;
    }
    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @Override
    public int getVMType(VMTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }

    @Override
    public String toString() {
        return competition.toString();
    }
}
