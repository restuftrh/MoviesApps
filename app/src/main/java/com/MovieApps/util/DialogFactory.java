package com.MovieApps.util;

import android.app.Dialog;
import android.content.Context;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

public final class DialogFactory {

    public static Dialog createSimpleOkDialog(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).setNeutralButton(com.MovieApps.R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

    public static Dialog createOkDialogWithCallback(Context context, String title, String message, AlertDialog.OnDismissListener listener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).setNeutralButton(com.MovieApps.R.string.dialog_action_ok, null).setOnDismissListener(listener);
        return alertDialog.create();
    }

    public static Dialog createGenericErrorDialogWithTitle(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, com.MovieApps.R.style.AppCompatAlertDialogErrorStyle).setTitle(title).setMessage(message).setNeutralButton(com.MovieApps.R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

    public static Dialog createGenericErrorDialog(Context context, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, com.MovieApps.R.style.AppCompatAlertDialogErrorStyle).setMessage(message).setNeutralButton(com.MovieApps.R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

    public static Snackbar showErrorSnackBar(Context mContext, View rootView, Throwable throwable) {
        String message = mContext.getString(com.MovieApps.R.string.dialog_general_error_message);
        if (throwable != null) {
            message = throwable.getLocalizedMessage();

        }
        Snackbar snack_error = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        View view = snack_error.getView();
        TextView tv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(mContext, com.MovieApps.R.color.material_red));
        return snack_error;
    }
}
