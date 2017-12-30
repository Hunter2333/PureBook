package com.blanke.purebook_android.manager;

import com.blanke.purebook_android.base.BaseColumnFragment;
import com.blanke.purebook_android.bean.BookColumn;
import com.blanke.purebook_android.constants.Constants;
import com.blanke.purebook_android.core.booklist.BookListFragment;
import com.blanke.purebook_android.core.nearmap.NearMapFragment;
import com.blanke.purebook_android.core.random.RandomFragment;
import com.blanke.purebook_android.core.taglist.TagListFragment;

public class ColumnManager {

    public static BaseColumnFragment getColumnFragment(BookColumn bookColumn) {
        switch (bookColumn.getType()) {
            case Constants.TYPE_COLUMN_BOOK:
                return BookListFragment.newInstance(bookColumn);
            case Constants.TYPE_COLUMN_TAG:
                return TagListFragment.newInstance(bookColumn);
            case Constants.TYPE_COLUMN_Random:
                return RandomFragment.newInstance(bookColumn);
            case Constants.TYPE_COLUMN_NEARMAP:
                return NearMapFragment.newInstance(bookColumn);
        }
        return BookListFragment.newInstance(bookColumn);
    }
}
