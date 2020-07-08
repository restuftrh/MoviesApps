package com.MovieApps.model.exception;

import com.MovieApps.model.common.ApiResponse;

public class ApiError extends Exception {

    private final ApiResponse apiResponse;

    public ApiError(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    public ApiResponse getApiResponse() {
        return apiResponse;
    }

    @Override
    public String getMessage() {
        if (apiResponse.getMessage() != null) {
            return apiResponse.getMessage();
        } else if (apiResponse.getError() != null) {
            return apiResponse.getError();
        } else {
            String s = "";
            for (int i = 0; i < apiResponse.getErrors().size(); i++) {
                if (i == 0) {
                    s += apiResponse.getErrors().get(i);
                } else {
                    s += "," + apiResponse.getErrors().get(i);
                }
            }
            return s;
        }
    }
}
