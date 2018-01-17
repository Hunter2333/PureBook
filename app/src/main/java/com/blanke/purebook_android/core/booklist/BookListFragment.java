package com.blanke.purebook_android.core.booklist;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.blanke.purebook_android.R;
import com.blanke.purebook_android.adapter.BookItemAdapter;
import com.blanke.purebook_android.base.BaseColumnFragment;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.core.booklist.presenter.BookListPresenter;
import com.blanke.purebook_android.core.booklist.presenter.BookListPresenterImpl;
import com.blanke.purebook_android.core.booklist.view.BookListView;
import com.blanke.purebook_android.core.details.DetailsActivity;
import com.blanke.purebook_android.core.details.DetailsActivity_;
import com.blanke.purebook_android.core.main.MainActivity_;
import com.blanke.purebook_android.utils.SkinUtils;
import com.blanke.purebook_android.utils.SnackUtils;
import com.neu.refresh.NeuSwipeRefreshLayout;
import com.neu.refresh.NeuSwipeRefreshLayoutDirection;
import com.socks.library.KLog;
import com.zhy.changeskin.SkinManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * 书的榜单Fragment
 * 继承BaseColumnFragment
 * 实现书的榜单View层接口
 * @author chrischen
 */
@EFragment(R.layout.fragment_book_item)
public class BookListFragment extends
        BaseColumnFragment<SwipeRefreshLayout, List<Book>, BookListView, BookListPresenter>
        implements BookListView, NeuSwipeRefreshLayout.OnRefreshListener {
    private static final long LAZY_DELAY_TIME = Constants.LAZY_DELAY_TIME;
    @ViewById(R.id.fragment_columnitem_recyclerview)
    FamiliarRecyclerView mRecyclerView;

    @ViewById(R.id.contentView)
    NeuSwipeRefreshLayout mSwipeRefreshLayout;
    private BookItemAdapter mAdapter;

    private int currentPage = 0;
    private int PAGE_COUNT = Constants.PAGE_COUNT;

    private boolean isNetworkFinish = false;

    public String getTitle() {
        return mCurrentBookColumn.getName();
    }

    public static BookListFragment newInstance(BookColumn bookColumn) {
        BookListFragment fragment = new BookListFragment_();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_BOOKCOLUMN, bookColumn);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int screenWidth = newConfig.screenWidthDp;
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(screenWidth / 150 + 1, OrientationHelper.VERTICAL));
    }

    @AfterViews
    void init() {
        EventBus.getDefault().register(this);
        applyTheme(null);
        mAdapter = new BookItemAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);//设置recycler view的adapter
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
                Book book = mAdapter.getBooks().get(position);
                DetailsActivity_.start(getActivity(),
                        (ImageView) view.findViewById(R.id.item_book_image), book);
                }
        });
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        SkinManager.getInstance().notifyChangedListeners();
    }

    @Subscriber(tag = Constants.EVENT_THEME_CHANGE)
    public void applyTheme(Object o) {
        mSwipeRefreshLayout.setProgressBackgroundColor(
                SkinUtils.getLoadProgressColorId(getContext()));
    }

    public void lazyLoad() {//fragment懒加载，可见才网络加载
        if (!isNetworkFinish) {
            mSwipeRefreshLayout.postDelayed(() -> mSwipeRefreshLayout.autoRefresh(), LAZY_DELAY_TIME);
            isNetworkFinish = true;
        }
        if (fab != null) {
            fab.setOnClickListener(v -> scrollTop());
            fab.attachToRecyclerView(mRecyclerView);
        }
    }

    public List<Book> getData() {
        return mAdapter.getBooks() == null ? null : new ArrayList<>(mAdapter.getBooks());
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public BookListPresenter createPresenter() {
        return new BookListPresenterImpl();
    }

    @Override
    public void setData(List<Book> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (data == null || data.size() == 0) {
            return;
        }
        if (currentPage == 0) {
            mAdapter.setData(data);
            mRecyclerView.postDelayed(() -> scrollTop(), 800);
        } else {
            mAdapter.addData(data);
        }
        currentPage++;
    }

    private void scrollTop() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    protected void showLightError(String msg) {
        mSwipeRefreshLayout.setRefreshing(false);
        SnackUtils.show(mRecyclerView, msg);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().getBookData(mCurrentBookColumn, pullToRefresh, PAGE_COUNT * currentPage, PAGE_COUNT);
    }

    @Override
    public void onRefresh(NeuSwipeRefreshLayoutDirection direction) {
        if (direction == NeuSwipeRefreshLayoutDirection.TOP) {
            currentPage = 0;
            loadData(true);
        } else if (direction == NeuSwipeRefreshLayoutDirection.BOTTOM) {
            loadData(true);
        }
    }

}
