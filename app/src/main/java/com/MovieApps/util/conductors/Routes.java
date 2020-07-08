package com.MovieApps.util.conductors;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler;

public class Routes {
    public static RouterTransaction simpleTransaction(Controller controller, ControllerChangeHandler changeHandler) {
        ControllerChangeHandler popChangeHandler;
        try {
            popChangeHandler = changeHandler.getClass().newInstance();
        } catch (Exception e) {
            popChangeHandler = new VerticalChangeHandler();
        }
        return RouterTransaction.with(controller)
                .pushChangeHandler(changeHandler)
                .popChangeHandler(popChangeHandler);
    }
}
