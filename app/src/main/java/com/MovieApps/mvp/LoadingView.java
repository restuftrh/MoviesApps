package com.MovieApps.mvp;

import net.derohimat.baseapp.view.BaseView;

public interface LoadingView extends BaseView {
    void showLoading(boolean isShow, int loadingType);
}
