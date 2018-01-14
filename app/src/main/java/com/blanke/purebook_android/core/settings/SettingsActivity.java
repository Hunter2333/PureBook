package com.blanke.purebook_android.core.settings;


import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.blanke.purebook_android.R;
import com.blanke.purebook_android.base.BaseSwipeBackActivity;
import com.blanke.purebook_android.utils.ResUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 设置Activity
 * 继承封装的BaseSwipeBackActivity
 * @author chrischen
 */

@EActivity(R.layout.activity_settings)
public class SettingsActivity extends BaseSwipeBackActivity {
    @ViewById(R.id.activity_settings_toolbar) Toolbar toolbar;

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setTitle(ResUtils.getResString(this, R.string.navigation_setting));
        getFragmentManager().beginTransaction().
                replace(R.id.activity_settings_layout, new SettingsFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}