package com.MovieApps.mvp;

import net.derohimat.baseapp.view.BaseView;

public interface ErrorView extends BaseView {
    void showError(Throwable throwable);
}
