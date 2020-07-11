package com.MovieApps.view.favorite;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.MovieApps.view.commons.ControllerActivity;
import com.MovieApps.view.main.MainController;
import com.bluelinelabs.conductor.Controller;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FavoriteActivity extends ControllerActivity {

    public static void start(Context context, int id) {
        Intent intent = new Intent(context, FavoriteActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    @Override
    protected Toolbar initToolbar(Toolbar toolbar) {
        toolbar.setVisibility(View.GONE);
        return toolbar;
    }

    @Override
    protected Controller getController() {
        return new FavoriteController(getIntent().getExtras());
    }

    /* --------------------------------------------------- */
    /* > Stater */
    /* --------------------------------------------------- */

//    public static void start(Context context) {
//        Intent intent = new Intent(context, LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        context.startActivity(intent);
//    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
