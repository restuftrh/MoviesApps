package com.MovieApps.util.rx;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import rx.Emitter;
import rx.Observable;
import rx.android.MainThreadSubscription;

public class RxEditText {
    public static Observable<String> onTextChange(EditText editText) {
        return Observable.create(emitter -> {
            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    emitter.onNext(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            };
            editText.addTextChangedListener(textWatcher);

            emitter.setSubscription(new MainThreadSubscription() {
                @Override
                protected void onUnsubscribe() {
                    editText.removeTextChangedListener(textWatcher);
                }
            });

            // first value
            emitter.onNext(editText.getText().toString());

        }, Emitter.BackpressureMode.LATEST);
    }
}
