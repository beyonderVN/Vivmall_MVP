package com.longngo.footballfan.data.datamanager;


import com.longngo.footballfan.data.model.Competition;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;

public interface FootballService {

    /**
     * Retrieve a list of competitions
     */
    @GET("competitions")
    Single<List<Competition>> getCompetitions(@Query("season") String season);


}
