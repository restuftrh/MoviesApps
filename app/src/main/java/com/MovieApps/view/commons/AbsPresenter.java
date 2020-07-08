package com.MovieApps.view.commons;

import com.MovieApps.util.rx.Subscriptions;

import net.derohimat.baseapp.presenter.BasePresenter;
import net.derohimat.baseapp.view.BaseView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class AbsPresenter<V extends BaseView> implements BasePresenter<V> {

    private V view;
    private CompositeSubscription subscription;

    @Override
    public void attachView(V v) {
        this.view = v;
    }

    @Override
    public void detachView() {
        this.view = null;
        onViewDetached();
    }

    protected V getView() {
        return view;
    }

    protected void onViewDetached() {
        unsubscribe();
    }

    protected void unsubscribe() {
        Subscriptions.unsubscribe(subscription);
    }

    protected void addSubscription(Subscription subscription) {
        getSubscription().add(subscription);
    }

    private CompositeSubscription getSubscription() {
        if (subscription == null) {
            subscription = new CompositeSubscription();
        }
        return subscription;
    }
}
