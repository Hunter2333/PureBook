package com.blanke.purebook_android.core.taglist;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.blanke.purebook_android.R;
import com.blanke.purebook_android.base.BaseColumnFragment;
import com.blanke.purebook_android.adapter.BaseRecyclerAdapter;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.bean.Tag;
import com.blanke.purebook_android.core.search.SearchResActivity_;
import com.blanke.purebook_android.core.taglist.presenter.TagListPresenter;
import com.blanke.purebook_android.core.taglist.presenter.TagListPresenterImpl;
import com.blanke.purebook_android.core.taglist.view.TagListView;
import com.blanke.purebook_android.utils.SnackUtils;
import com.joanzapata.android.recyclerview.BaseAdapterHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

@EFragment(R.layout.fragment_tag_item)
public class TagListFragment extends BaseColumnFragment<LinearLayout, List<Tag>, TagListView, TagListPresenter>
        implements TagListView {
    @ViewById(R.id.fragment_tag_recyclerview)
    FamiliarRecyclerView mRecyclerView;
    private BaseRecyclerAdapter<Tag> mAdapter;
    private boolean isFirstNetworkFinish = false;

    public static TagListFragment newInstance(BookColumn bookColumn) {
        TagListFragment fragment = new TagListFragment_();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_BOOKCOLUMN, bookColumn);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int screenWidth = newConfig.screenWidthDp;
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(screenWidth / 150 + 1, OrientationHelper.VERTICAL));
    }

    @AfterViews
    void init() {
        mAdapter = new BaseRecyclerAdapter<Tag>(getActivity(), R.layout.item_recyclerview_tag) {
            @Override
            protected void convert(BaseAdapterHelper helper, Tag item) {
                helper.getTextView(R.id.item_tag_title).setText(item.getName());
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
                SearchResActivity_.intent(TagListFragment.this)
                        .key(mAdapter.getItem(position).getName()).start();
            }
        });
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
    }

    private void scrollTop() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public TagListPresenter createPresenter() {
        return new TagListPresenterImpl();
    }

    @Override
    public void setData(List<Tag> data) {
        if(data!=null&&data.size()>0) {
            mAdapter.addAll(data);
            isFirstNetworkFinish = true;
        }
    }

    @Override
    protected void showLightError(String msg) {
        SnackUtils.show(mRecyclerView, msg);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().getTagData(pullToRefresh, 0, 100);
    }

    @Override
    protected void lazyLoad() {
        if (!isFirstNetworkFinish) {
            loadData(false);
        }
        if (fab != null) {
            fab.attachToRecyclerView(mRecyclerView);
            fab.setOnClickListener(v -> scrollTop());
        }
    }
}
