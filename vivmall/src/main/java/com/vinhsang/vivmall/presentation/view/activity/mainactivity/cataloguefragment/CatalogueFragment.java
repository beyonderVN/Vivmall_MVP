package com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vinhsang.vivmall.R;
import com.vinhsang.vivmall.presentation.MainApplication;
import com.vinhsang.vivmall.presentation.view.activity.base.BaseFragment;
import com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment.adapter.ItemProductsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.gujun.android.taggroup.TagGroup;


public class CatalogueFragment extends BaseFragment<CataloguePresentationModel, CatalogueView, CataloguePresenter> implements CatalogueView, TagGroup.OnTagClickListener {
    private static final String TAG = CatalogueFragment.class.getCanonicalName();


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
    @BindView(R.id.progress_bar)
    FrameLayout processBar;

    @OnClick(R.id.expand_button)
    void seleteCatalogue() {
        boolean isVisible = (mTagGroup.getVisibility() == View.GONE);
        if (isVisible) {
            mTagGroup.setVisibility(View.VISIBLE);
        } else {
            mTagGroup.setVisibility(View.GONE);
        }
    }

    ItemProductsAdapter itemProductsAdapter;

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
        return view;

    }
    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(itemProductsAdapter);
        final StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        2, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        staggeredGridLayoutManagerVertical.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        staggeredGridLayoutManagerVertical.invalidateSpanAssignments();
        final RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            // The minimum amount of items to have below your current scroll position
            // before loading more.
            private int visibleThreshold = 5;
            // The current offset index of data you have loaded
            private int currentPage = 0;
            // The total number of items in the dataset after the last load
            private int previousTotalItemCount = 0;
            // True if we are still waiting for the last set of data to load.
            private boolean loading = true;
            // Sets the starting page index
            private int startingPageIndex = 0;
            RecyclerView.LayoutManager mLayoutManager = staggeredGridLayoutManagerVertical;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = 0;
                int totalItemCount = mLayoutManager.getItemCount();
                int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(null);
                // get maximum element within the list
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
                if (totalItemCount < previousTotalItemCount) {
                    this.currentPage = this.startingPageIndex;
                    this.previousTotalItemCount = totalItemCount;
                    if (totalItemCount == 0) {
                        this.loading = true;
                    }
                }
                // If it’s still loading, we check to see if the dataset count has
                // changed, if so we conclude it has finished loading and update the current page
                // number and total item count.
                if (loading && (totalItemCount > previousTotalItemCount)) {
                    loading = false;
                    previousTotalItemCount = totalItemCount;
                }

                // If it isn’t currently loading, we check to see if we have breached
                // the visibleThreshold and need to reload more data.
                // If we do need to reload some more data, we execute onUpdate to fetch the data.
                // threshold should reflect how many total columns there are too
                if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
                    currentPage++;
                    onLoadMore(currentPage, totalItemCount);
                    loading = true;
                }

            }

            private void onLoadMore(int currentPage, int totalItemCount) {
                try {
                    presenter.loadMore();
                } catch (Exception e) {
                    e.getStackTrace();
                }

            }

            public int getLastVisibleItem(int[] lastVisibleItemPositions) {
                int maxSize = 0;
                for (int i = 0; i < lastVisibleItemPositions.length; i++) {
                    if (i == 0) {
                        maxSize = lastVisibleItemPositions[i];
                    } else if (lastVisibleItemPositions[i] > maxSize) {
                        maxSize = lastVisibleItemPositions[i];
                    }
                }
                return maxSize;
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
                    hideCatalogue();
                }
                Log.d(TAG, "onTouch: " + (yU - yD));
                return false;
            }
        });
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
        hideCatalogue();
        presenter.resetRecyclerViewByNewTag(tag);
    }


    private void hideProcess() {
        processBar.setVisibility(View.GONE);
    }


    @Override
    public void showProgress() {
        processBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent() {
        itemProductsAdapter = new ItemProductsAdapter(presenter.getPresentationModel().getmItemProducts());
        recyclerView.setAdapter(itemProductsAdapter);
        mTagGroup.setTags(presenter.getPresentationModel().getListTag());
        mTagGroup.setOnTagClickListener(this);
        catalogueName.setText(presenter.getPresentationModel().getCurrentTag());
        hideProcess();
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
    public void onLoadMore() {
        Log.d(TAG, "onUpdate: " + presenter.getPresentationModel().getmItemProducts().size());
        itemProductsAdapter.notifyDataSetChanged();

    }

    @Override
    public void showLoadingMore() {

    }
}
