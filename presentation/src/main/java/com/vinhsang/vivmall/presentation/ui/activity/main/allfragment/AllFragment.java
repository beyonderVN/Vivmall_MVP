package com.vinhsang.vivmall.presentation.ui.activity.main.allfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import com.vinhsang.vivmall.presentation.MainApplication;
import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.ui.activity.base.BaseFragment;
import com.vinhsang.vivmall.presentation.ui.activity.main.allfragment.adapter.ItemProductAllAdapter;
import com.vinhsang.vivmall.presentation.ui.widget.recyclerviewhelper.InfiniteScrollListener;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

//TODO: TRONG KHI LOAD MORE NEU VIEW BI DETROY TRANG THAI LOAD MORE SE BI DU LAI KHI VIEW DC TAO LAI

public class AllFragment extends BaseFragment<AllPresentationModel, AllView, AllPresenter>
        implements AllView {
    private static final String TAG = "AllFragment";
    private static final int POSITION_CONTENT_VIEW = 0;
    private static final int POSITION_PROGRESS_VIEW = 1;

    private static final String ARG_POSITION = "position";
    View view;
    @BindView(R.id.recycler_detail)
    RecyclerView recyclerView;
    @BindInt(R.integer.column_num)
    int columnNum;
    @BindView(R.id.viewAnimator)
    ViewAnimator resultAnimator;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    ItemProductAllAdapter itemProductsAdapter;
    AllPresentationModel allPresentationModel;

    public static AllFragment newInstance(int position) {
        AllFragment f = new AllFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    public static AllFragment newInstance() {
        return new AllFragment();
    }

    public AllFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.bind(this, view);
            setupRecyclerView(recyclerView);
            setupSwipeRefreshLayout(swipeRefresh);

        }

        return view;

    }

    void setupRecyclerView(RecyclerView recyclerView) {

        final StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        columnNum, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        staggeredGridLayoutManagerVertical.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        staggeredGridLayoutManagerVertical.invalidateSpanAssignments();
        RecyclerView.LayoutManager layoutGridManager = new GridLayoutManager(getActivity(), columnNum);
        recyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
        recyclerView.setHasFixedSize(true);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(3000);
        itemAnimator.setRemoveDuration(3000);
        recyclerView.setItemAnimator(itemAnimator);

        recyclerView.addOnScrollListener(new InfiniteScrollListener(staggeredGridLayoutManagerVertical) {
            @Override
            public void onLoadMore() {
                try {
                    presenter.loadMore();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            @Override
            public boolean isLoading() {
                return itemProductsAdapter.isLoadingMore();
            }

            @Override
            public boolean isNoMore() {
                return allPresentationModel.isNoMore();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    //IMPORTANT run after presenter.attachView()
    @Override
    public void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();




        if(allPresentationModel==null){
            allPresentationModel = presenter.getPresentationModel();
            itemProductsAdapter = new ItemProductAllAdapter(getActivity(),allPresentationModel,navigator);
            recyclerView.setAdapter(itemProductsAdapter);
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showProgress() {
        resultAnimator.setDisplayedChild(POSITION_PROGRESS_VIEW);
    }

    @Override
    public void showContent() {
        resultAnimator.setDisplayedChild(POSITION_CONTENT_VIEW);
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void onFetchError() {

    }


    @Override
    public void startLoadingMore() {
        itemProductsAdapter.dataStartedLoading();
    }

    @Override
    public void onUpdate() {
        itemProductsAdapter.notifyDataSetChanged();
        recyclerView.setLayoutFrozen(false);
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void finishLoadingMore() {
        itemProductsAdapter.dataFinishedLoading();

    }

    @Override
    protected void performFieldInection() {
        MainApplication.getMainComponent().inject(this);
    }

    @NonNull
    @Override
    protected AllPresentationModel createPresentationModel() {
        return new AllPresentationModel("All");
    }

    @Override
    public void onDestroyView() {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        finishLoadingMore();
        super.onDestroyView();
    }


    public void setupSwipeRefreshLayout(final SwipeRefreshLayout upSwipeRefreshLayout) {
        upSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        upSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.setLayoutFrozen(true);
                upSwipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        upSwipeRefreshLayout.setRefreshing(false);
                        Log.d("Swipe", "Refreshing Number");
                        itemProductsAdapter.setLoadingMore(false);
                        presenter.resetData();
                    }
                }, 500);

            }
        });
    }

}
