package com.MovieApps.model.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse<R> {
    @SerializedName("message") private String message;
    @SerializedName("error") private String error;
    @SerializedName("errors") private List<String> errors;
    @SerializedName("status") private int httpStatus;
    @SerializedName("data") private R data;

    public R getData() {
        return data;
    }

    public void setData(R data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
