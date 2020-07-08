package com.MovieApps.util.conductors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.View;

import com.bluelinelabs.conductor.Controller;
import com.MovieApps.view.commons.ControllerActivity;

import rx.functions.Action0;
import rx.functions.Func1;

public class Controllers {

//    public static void editToolbar(Controller controller, Action1<Toolbar> fun) {
//        if (!controller.isDestroyed() && controller.getActivity() instanceof ControllerActivity) {
//            ControllerActivity controllerActivity = (ControllerActivity) controller.getActivity();
//            controllerActivity.editToolbar(fun);
//        }
//    }

    public static void callWhenViewReady(Controller controller, Action0 action0) {
        controller.addLifecycleListener(new Controller.LifecycleListener() {
            @Override
            public void postCreateView(@NonNull Controller controller, @NonNull View view) {
                action0.call();
                controller.removeLifecycleListener(this);
            }
        });
    }

    public static void setResultAndFinish(Controller controller, Func1<Bundle, Bundle> onReturnBundle) {
        if (isControllerAvailable(controller)) {
            ControllerActivity controllerActivity = (ControllerActivity) controller.getActivity();
            if (controllerActivity != null) {
                controllerActivity.setResult(Activity.RESULT_OK, new Intent().putExtras(onReturnBundle.call(new Bundle())));
                controllerActivity.finish();
            }
        }
    }

    private static boolean isControllerAvailable(Controller controller) {
        return !controller.isDestroyed() && controller.getActivity() instanceof ControllerActivity;
    }
}
