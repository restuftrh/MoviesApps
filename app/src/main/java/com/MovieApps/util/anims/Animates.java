package com.MovieApps.util.anims;

import androidx.core.view.ViewCompat;
import android.view.View;

public class Animates {

    public static void visibility(final View view, final boolean isVisible) {
        if (isVisible) {
            ViewCompat.setAlpha(view, 0);
            view.setVisibility(View.VISIBLE);
        }

        final int duration = 200;
        final float alpha = isVisible ? 1f : 0f;

        ViewCompat.animate(view)
                .setDuration(duration)
                .alpha(alpha)
                .withEndAction(() -> {
                    if (!isVisible) {
                        view.setVisibility(View.GONE);
                    }
                });
    }
}
