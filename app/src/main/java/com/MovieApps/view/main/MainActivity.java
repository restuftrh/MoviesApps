package com.MovieApps.view.main;

import android.content.Context;
import android.content.Intent;

import com.bluelinelabs.conductor.Controller;
import com.MovieApps.view.commons.MainControllerActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends MainControllerActivity{



    public static void start(Context context) {
        context.startActivity(new Intent(
                context,
                MainActivity.class
        ));
    }

    public static void start(Context context, String sortcut) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MainController.class.getName(),sortcut);
        context.startActivity(intent);
    }

    /* --------------------------------------------------- */
    /* > Stater */
    /* --------------------------------------------------- */

    @Override
    protected Controller getController() {
        return new MainController(getIntent().getExtras());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
