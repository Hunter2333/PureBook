package com.blanke.purebook_android.core.userhome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blanke.purebook_android.R;
import com.blanke.purebook_android.adapter.BaseRecyclerAdapter;
import com.blanke.purebook_android.base.BaseFragment;
import com.blanke.purebook_android.bean.Book;
import com.blanke.purebook_android.bean.BookBean;
import com.blanke.purebook_android.bean.BookComment;
import com.blanke.purebook_android.bean.BookReviewBean;
import com.blanke.purebook_android.bean.User;
import com.blanke.purebook_android.bean.UserBean;
import com.blanke.purebook_android.bean.UserBookLike;
import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.core.details.DetailsActivity;
import com.blanke.purebook_android.core.login.LoginActivity;
import com.blanke.purebook_android.core.userhome.presenter.NewlyCommentPresenterImpl;
import com.blanke.purebook_android.core.userhome.presenter.NewlyLikePresenterImpl;
import com.blanke.purebook_android.core.userhome.presenter.UserNewlyPresenter;
import com.blanke.purebook_android.core.userhome.view.UserNewlyView;
import com.blanke.purebook_android.utils.DateUtils;
import com.blanke.purebook_android.utils.ResUtils;
import com.blanke.purebook_android.utils.SkinUtils;
import com.blanke.purebook_android.web.ApiService;
import com.blanke.purebook_android.web.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.recyclerview.BaseAdapterHelper;
import com.neu.refresh.NeuSwipeRefreshLayout;
import com.neu.refresh.NeuSwipeRefreshLayoutDirection;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.changeskin.SkinManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */


//TODO:
@EFragment(R.layout.fragment_userhome_newly)
public class UserNewlyFragment extends BaseFragment
        implements NeuSwipeRefreshLayout.OnRefreshListener, UserNewlyView {
    private static String ARG_POSITION = "UserNewlyFragment_ARG_POSITION";
    private static String ARG_USERID = "UserNewlyFragment_ARG_USERID";
    private int position;
    @ViewById(R.id.fragment_newly_swipelayout)
    NeuSwipeRefreshLayout mSwipeRefreshLayout;
    @ViewById(R.id.fragment_newly_recyclerview)
    FamiliarRecyclerView mRecyclerView;

    private BaseRecyclerAdapter mAdapter;

    //绑定Presenter
    private UserNewlyPresenter mPresenter;
    private int userId;
    private int currentPage = 0;
    private int count = Constants.PAGE_COUNT;

    public static UserNewlyFragment newInstance(int position, UserBean user) {
        UserNewlyFragment fragment = new UserNewlyFragment_();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        bundle.putInt(ARG_USERID,user.getUserID());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        position = args.getInt(ARG_POSITION);
        userId = args.getInt(ARG_USERID);
    }

    public void changeTheme(Object o) {
        mSwipeRefreshLayout.setProgressBackgroundColor(SkinUtils.getLoadProgressColorId(getContext()));
    }

    @AfterViews
    public void init() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        changeTheme(null);
        initAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
                Object o = mAdapter.getItem(position);
                Book book = null;
                if (o instanceof UserBean) {
                    //TODO:
                    UserBean like = (UserBean) o;
                    //book = like.getBook();
                } else if (o instanceof BookReviewBean) {
                    //TODO:
                    BookReviewBean review = (BookReviewBean) o;
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.REQUEST_HTTP_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ApiService apiService = retrofit.create(ApiService.class);
                    Call<BaseResponse<BookBean>> call = apiService.getBookById(review.getBookId());
                    call.enqueue(new Callback<BaseResponse<BookBean>>() {
                        @Override
                        public void onResponse(Call<BaseResponse<BookBean>> call, Response<BaseResponse<BookBean>> response) {
                            if(response.body().getCode().toString().equals("200")){
                                //book.setAuthor()
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse<BookBean>> call, Throwable t) {

                        }
                    });
                    //book = comment.getBook();
                }
                //DetailsActivity.start(getActivity(), (ImageView) view.findViewById(R.id.item_newly_booklike_img), book);
            }
        });
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        mSwipeRefreshLayout.postDelayed(() -> mSwipeRefreshLayout.autoRefresh(), 100);
    }

    private void initAdapter() {
        if (position == 0) {
            mPresenter = new NewlyLikePresenterImpl(this);
            mAdapter = new BaseRecyclerAdapter<BookBean>(getActivity(), R.layout.item_newly_booklike) {
                @Override
                protected void convert(BaseAdapterHelper helper, BookBean item) {
                    ImageView img = helper.getImageView(R.id.item_newly_booklike_img);
                    ImageLoader.getInstance().displayImage(item.getCover(), img, Constants.getImageOptions());
                    helper.getTextView(R.id.item_newly_booklike_title).setText(item.getBookName());
                    //TODO:喜欢的时间
                    helper.getTextView(R.id.item_newly_booklike_time).setText("");
                    SkinManager.getInstance().injectSkin(img.getRootView());
                }
            };
        } else {
            mPresenter = new NewlyCommentPresenterImpl(this);
            mAdapter = new BaseRecyclerAdapter<BookReviewBean>(getActivity(), R.layout.item_newly_booklike) {
                @Override
                protected void convert(BaseAdapterHelper helper, BookReviewBean item) {
                    ImageView img = helper.getImageView(R.id.item_newly_booklike_img);
                    //TODO:
                    ImageLoader.getInstance().displayImage("", img, Constants.getImageOptions());
                    //TODO:
                    helper.getTextView(R.id.item_newly_booklike_title)
                            .setText("");
                    helper.getTextView(R.id.item_newly_booklike_time)
                            .setText(DateUtils.date2String(item.getTime(),"yyyy-MM-dd HH:mm:ss"));
                    helper.getTextView(R.id.item_newly_booklike_content)
                            .setText(ResUtils.getResString(getActivity(), R.string.title_comment)
                                    + ":" + item.getReview());
                    SkinManager.getInstance().injectSkin(img.getRootView());
                }
            };
        }
    }



    @Override
    public void onRefresh(NeuSwipeRefreshLayoutDirection neuSwipeRefreshLayoutDirection) {
        mPresenter.loadData(userId, currentPage * count, count);
    }


    @Override
    public void setLikeData(List<BookBean> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        mAdapter.addAll(data);
        currentPage++;
    }

    @Override
    public void setCommentData(List<BookReviewBean> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        mAdapter.addAll(data);
        currentPage++;
    }

    @Override
    public void setLoading(boolean isLoading) {
        mSwipeRefreshLayout.setRefreshing(isLoading);
    }
}
