package com.MovieApps.events;

public class RefreshEvent {
    private boolean valid;

    public RefreshEvent(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }
}
