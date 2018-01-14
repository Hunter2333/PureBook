package com.blanke.purebook_android.core.nearmap.presenter;

import com.avos.avoscloud.AVGeoPoint;
import com.blanke.purebook_android.core.nearmap.view.NearMapView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

//TODO:
public abstract class NearMapPresenter extends MvpBasePresenter<NearMapView> {
    public abstract void getNearFriend(boolean pullToRefresh, AVGeoPoint location, int size);
}
