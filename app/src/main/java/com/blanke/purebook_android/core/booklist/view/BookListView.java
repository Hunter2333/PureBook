package com.blanke.purebook_android.core.booklist.view;

import com.blanke.purebook_android.bean.Book;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

/**
 * 书的榜单View层接口
 * 继承MvpLceView
 * @author chrischen
 */
public interface BookListView extends MvpLceView<List<Book>> {

}
