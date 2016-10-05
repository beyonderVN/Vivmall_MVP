package com.longngo.footballfan.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.longngo.footballfan.R;
import com.longngo.footballfan.data.datamanager.DataManager;
import com.longngo.footballfan.data.model.Competition;
import com.longngo.footballfan.ui.FootballFanApplication;
import com.longngo.footballfan.ui.adapter.CompetitionListAdapter;
import com.longngo.footballfan.ui.viewmodel.mapper.Mapper;
import com.longngo.footballfan.ui.viewmodel.vmfactory.Visitable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.list)
    RecyclerView listRV;
    DataManager mDataManager ;
    CompetitionListAdapter competitionListAdapter;
    List<Visitable> visitables = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mDataManager = FootballFanApplication.getMainComponent().dataManager();
        listRV.setLayoutManager( new LinearLayoutManager(this));
        competitionListAdapter = new CompetitionListAdapter(visitables);
        listRV.setAdapter(competitionListAdapter);
        getCompetitions("2016");


    }



    public void getCompetitions(String season) {
        mDataManager.getCompetitions(season)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<List<Competition>, List<Visitable>>() {
                    @Override
                    public List<Visitable> call(List<Competition> competitions) {
                        return Mapper.tran(competitions);
                    }
                })
                .subscribe(new SingleSubscriber<List<Visitable>>() {
                    @Override
                    public void onSuccess(List<Visitable> competitions) {

                        if (!competitions.isEmpty()) {
                            Log.d(TAG, "onSuccess: "+competitions.size());
                            visitables.addAll(competitions);
                            competitionListAdapter.notifyDataSetChanged();

                        } else {
                            Log.d(TAG, "onSuccess: is empty");
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.d(TAG, "onError: ");
                    }
                });
    }
}
