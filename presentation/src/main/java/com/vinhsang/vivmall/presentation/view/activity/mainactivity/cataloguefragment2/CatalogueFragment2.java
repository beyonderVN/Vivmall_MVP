package com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import com.vinhsang.vivmall.presentation.MainApplication;
import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.view.activity.base.BaseFragment;
import com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment2.adapter.ItemProductCatalogueAdapter2;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;


public class CatalogueFragment2 extends BaseFragment<CataloguePresentationModel2, CatalogueView2, CataloguePresenter2> implements CatalogueView2 {
    private static final String TAG = "CatalogueFragment2";
    private static final int POSITION_CONTENT_VIEW  = 0;
    private static final int POSITION_PROGRESS_VIEW = 1;

    private static final String ARG_POSITION = "position";


    View view;
    @BindView(R.id.recycler_detail)
    RecyclerView recyclerView;
    @BindView(R.id.resultAnimator)
    ViewAnimator resultAnimator;
    @BindInt(R.integer.column_num)
    int columnNum;


//    @BindView(R.id.swipe_refresh)
//    SwipeRefreshLayout swipeRefresh;


    ItemProductCatalogueAdapter2 itemProductsAdapter ;
    CataloguePresentationModel2 cataloguePresentationModel;
    public static CatalogueFragment2 newInstance() {
        CatalogueFragment2 f = new CatalogueFragment2();

        return f;
    }

    public CatalogueFragment2() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }

    @Override
    protected void performFieldInection() {
        MainApplication.getMainComponent().inject(this);
    }

    @NonNull
    @Override
    protected CataloguePresentationModel2 createPresentationModel() {
        return new CataloguePresentationModel2("Catalogue");
    }


    @Override
    public void onDestroyView() {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }

        super.onDestroyView();
    }

    //IMPORTANT run after presenter.attachView()
    @Override
    public void onStart() {
        super.onStart();
        cataloguePresentationModel = presenter.getPresentationModel();
        itemProductsAdapter = new ItemProductCatalogueAdapter2(cataloguePresentationModel);
        recyclerView.setAdapter(itemProductsAdapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sub2, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();

        return view;

    }

    public void setupSwipeRefreshLayout(final SwipeRefreshLayout upSwipeRefreshLayout) {
        upSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        upSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ( new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        upSwipeRefreshLayout.setRefreshing(true);

                    }
                }, 1000);

            }
        });

    }
    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

//        recyclerView.addOnScrollListener(new InfiniteScrollListener(staggeredGridLayoutManagerVertical) {
//            @Override
//            public void onLoadMore() {
//                try {
//                    presenter.loadMore();
//                } catch (Exception e) {
//                    e.getStackTrace();
//                }
//            }
//
//            @Override
//            public boolean isLoading() {
//                return itemProductsAdapter.isLoadingMore();
//            }
//
//            @Override
//            public boolean isNoMore() {
//                return false;
//            }
//        });


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void init() {

    }

    @Override
    public void loadListTag() {

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
    public void onFetchError() {

    }

    @Override
    public void finishLoadingMore() {
        itemProductsAdapter.dataFinishedLoading();
    }

    @Override
    public void startLoadingMore() {
        itemProductsAdapter.dataStartedLoading();
    }

    @Override
    public void onUpdate() {
        itemProductsAdapter.upDate();
        //swipeRefresh.setRefreshing(false);
    }


}
