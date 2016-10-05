package com.longngo.footballfan.data.datamanager;


import com.longngo.footballfan.data.model.Competition;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Single;

@Singleton
public class DataManager {

    private final FootballService mFootballService;

    @Inject
    public DataManager(FootballService footballService) {
        mFootballService = footballService;
    }

    public Single<List<Competition>> getCompetitions(String season) {
        return mFootballService.getCompetitions(season);
    }

}