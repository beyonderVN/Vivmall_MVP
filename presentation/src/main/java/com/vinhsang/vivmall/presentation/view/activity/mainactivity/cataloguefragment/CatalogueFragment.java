package com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.vinhsang.vivmall.presentation.MainApplication;
import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment.adapter.ItemProductCatalogueAdapter;
import com.vinhsang.vivmall.presentation.view.recyclerviewhelper.InfiniteScrollListener;
import com.vinhsang.vivmall.presentation.view.activity.base.BaseFragment;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.gujun.android.taggroup.TagGroup;


public class CatalogueFragment extends BaseFragment<CataloguePresentationModel, CatalogueView, CataloguePresenter> implements CatalogueView, TagGroup.OnTagClickListener {
    private static final String TAG = CatalogueFragment.class.getCanonicalName();
    private static final int POSITION_CONTENT_VIEW  = 0;
    private static final int POSITION_PROGRESS_VIEW = 1;

    private static final String ARG_POSITION = "position";


    View view;
    @BindView(R.id.recycler_detail)
    RecyclerView recyclerView;
    @BindView(R.id.catalogue)
    LinearLayout layoutCatalogue;
    @BindView(R.id.expand_button)
    ImageView expandButton;
    @BindView(R.id.tag_group)
    TagGroup mTagGroup;
    @BindView(R.id.catalogue_name)
    TextView catalogueName;
    @BindView(R.id.resultAnimator)
    ViewAnimator resultAnimator;
    @BindInt(R.integer.column_num)
    int columnNum;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @OnClick(R.id.expand_button)
    void seleteCatalogue() {
        boolean isVisible = (mTagGroup.getVisibility() == View.GONE);
        if (isVisible) {
            mTagGroup.setVisibility(View.VISIBLE);
        } else {
            mTagGroup.setVisibility(View.GONE);
        }
    }

    ItemProductCatalogueAdapter itemProductsAdapter = new ItemProductCatalogueAdapter();

    public static CatalogueFragment newInstance() {
        CatalogueFragment f = new CatalogueFragment();

        return f;
    }

    public CatalogueFragment() {
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
    protected CataloguePresentationModel createPresentationModel() {
        return new CataloguePresentationModel("Catalogue");
    }


    @Override
    public void onDestroyView() {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }

        super.onDestroyView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sub, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        mTagGroup.setOnTagClickListener(this);
        setupSwipeRefreshLayout(swipeRefresh);
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
                        Log.d("Swipe", "Refreshing Number");
                        presenter.resetRecyclerView();
                    }
                }, 1000);

            }
        });
    }
    private void setupRecyclerView() {
        recyclerView.setAdapter(itemProductsAdapter);
        recyclerView.setHasFixedSize(true);
        final StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        columnNum, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        staggeredGridLayoutManagerVertical.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        staggeredGridLayoutManagerVertical.invalidateSpanAssignments();
        final RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), columnNum);
        recyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);

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
                return itemProductsAdapter.isShowLoadingMore();
            }
        });
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            float yD, yU;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    yD = motionEvent.getY();
                }
                yU = motionEvent.getY();
                if (yU - yD > 30) {
                    showCatalogue();
                }
                if (yU - yD < -30) {
                    hideTagGroup();
                    hideCatalogue();
                }
                Log.d(TAG, "onTouch: " + (yU - yD));
                return false;
            }
        });
    }

    private void hideTagGroup() {
        if(mTagGroup.getVisibility()!= View.GONE){
            mTagGroup.setVisibility(View.GONE);
        }

    }
    private void hideCatalogue() {
        if(layoutCatalogue.getVisibility()!= View.GONE){
            layoutCatalogue.setVisibility(View.GONE);
        }
        if(mTagGroup.getVisibility()!= View.GONE){
            mTagGroup.setVisibility(View.GONE);
        }
    }
    private void showCatalogue() {
        if(layoutCatalogue.getVisibility()!= View.VISIBLE){
            layoutCatalogue.setVisibility(View.VISIBLE);
        }
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
    public void onTagClick(String tag) {
        Log.d(TAG, "onTagClick: ");
        catalogueName.setText(tag);
        hideTagGroup();
        presenter.resetRecyclerViewByNewTag(tag);
    }


    @Override
    public void init() {
        Log.d(TAG, "init: ");
        itemProductsAdapter.setmItemProducts(presenter.getPresentationModel().getmItemProducts());
        itemProductsAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadListTag() {
        Log.d(TAG, "loadListTag: ");
        mTagGroup.setTags(presenter.getPresentationModel().getListTagString());
        catalogueName.setText(presenter.getPresentationModel().getCurrentTag());
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
        itemProductsAdapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }
}
