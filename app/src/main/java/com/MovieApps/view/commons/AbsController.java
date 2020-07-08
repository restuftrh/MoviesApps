package com.MovieApps.view.commons;

import android.app.Activity;
import android.content.ContextWrapper;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.rxlifecycle.RxController;
import com.MovieApps.CrsApp;
import com.MovieApps.di.component.ActivityComponent;
import com.MovieApps.di.component.ControllerComponent;
import com.MovieApps.di.module.ControllerModule;
import com.MovieApps.view.AppBaseActivity;

import net.derohimat.baseapp.view.BaseView;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public abstract class AbsController extends RxController {

    public AbsController() {
        onInit();
    }

    public AbsController(Bundle args) {
        super(args);
        onInit();
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        onSetupComponent();
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        onViewBound(view);
        setupEventBus();
        return view;
    }

    private void setupEventBus() {
        if (enableEventBus()) {
            addLifecycleListener(new LifecycleListener() {
                @Override
                public void postAttach(@NonNull Controller controller, @NonNull View view) {
                    super.postAttach(controller, view);

                    EventBus eventBus = CrsApp.component().eventBus();
                    if (!eventBus.isRegistered(AbsController.this)) {
                        eventBus.register(AbsController.this);
                    }
                }

                @Override
                public void postDetach(@NonNull Controller controller, @NonNull View view) {
                    super.postDetach(controller, view);
                    CrsApp.component()
                            .eventBus()
                            .unregister(AbsController.this);
                }
            });
        }
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        ButterKnife.unbind(this);
    }

    protected boolean enableEventBus() {
        return false;
    }

    protected void onInit() {
        // to be overrided
    }

    protected abstract void onCreate(Bundle savedInstanceState);

    protected void onSetupComponent() {
        //to be overrided
    }

    protected abstract int getLayoutResId();

    protected abstract void onViewBound(View view);

    /* --------------------------------------------------- */
    /* > Components */
    /* --------------------------------------------------- */

    public ActivityComponent getActivityComponent() {
        AppBaseActivity absDriverAct = (AppBaseActivity) getActivity();
        if (absDriverAct == null) {
            throw new IllegalStateException("Activity should not be null at this state. Please use this on #onSetupComponent");
        }
        return absDriverAct.getComponent();
    }

    protected ControllerComponent getComponent() {
        return getActivityComponent().controllerComponent()
                .controllerModule(new ControllerModule(this))
                .build();
    }

    /* --------------------------------------------------- */
    /* > Helper */
    /* --------------------------------------------------- */

    @SuppressWarnings("unchecked")
    protected void bindPresenter(BaseView mvpView, AbsPresenter presenter) {
        presenter.attachView(mvpView);

        addLifecycleListener(new LifecycleListener() {
            @Override
            public void preAttach(@NonNull Controller controller, @NonNull View view) {
                super.preAttach(controller, view);
                presenter.attachView(mvpView);
            }

            @Override
            public void postDestroy(@NonNull Controller controller) {
                super.postDestroy(controller);
                presenter.detachView();
            }
        });
    }

    protected void finishActivity() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    protected void popCurrentController() {
        if (getRouter().getBackstackSize() > 0) {
            getRouter().popCurrentController();
        }
    }

    @Nullable
    public FragmentManager getSupportFragmentManager() {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity == null) {
            return null;
        }
        return appCompatActivity.getSupportFragmentManager();
    }

    protected void attachBaseContext(ContextWrapper wrap) {
    }
}
