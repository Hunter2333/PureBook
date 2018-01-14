package com.blanke.purebook_android.core.main.view;

import com.blanke.purebook_android.bean.BookColumn;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

/**
 * Main的View层接口
 * 继承Mosby库的MvpLceView
 */
public interface MainView extends MvpLceView<List<BookColumn>> {
}
