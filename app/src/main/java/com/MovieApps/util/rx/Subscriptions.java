package com.MovieApps.util.rx;

import rx.Subscription;

public class Subscriptions {

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
