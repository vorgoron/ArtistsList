package com.vorgoron.artistslist.handler;

import lombok.Getter;

public class ErrorHandler {

//    private final Context context;
    @Getter
    private String errorMessage = "";
    @Getter
    private int errorStatus;

//    public ErrorHandler(Context context, Throwable error)  {
//        this.context = context;
//        try {
//            if (error instanceof RetrofitError) {
//                detailRetrofitError((RetrofitError) error);
//            } else {
//                errorMessage = context.getString(R.string.error_message_unknown_error);
//            }
//            error.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//            errorMessage = context.getString(R.string.error_message_unknown_error);
//        }
//    }
//
//    private void detailRetrofitError(RetrofitError retrofitError) {
//        RetrofitError.Kind kind = retrofitError.getKind();
//        switch (kind) {
//            case NETWORK:
//                errorMessage = context.getString(R.string.error_message_internet_is_unavailable);
//                break;
//            case CONVERSION:
//                errorMessage = kind.name();
//                break;
//            case HTTP:
//                detailHttpRetrofitError(retrofitError);
//                break;
//            case UNEXPECTED:
//                errorMessage = kind.name();
//                break;
//        }
//    }
//
//    private void detailHttpRetrofitError(RetrofitError retrofitError)  {
//        if (retrofitError.getResponse() == null) {
//            errorMessage = context.getString(R.string.error_message_http_error);
//        } else {
//            try {
//                ErrorResponse errorResponse = (ErrorResponse) retrofitError.getBodyAs(ErrorResponse.class);
//                errorMessage = errorResponse.getMessage();
//                errorStatus = retrofitError.getResponse().getStatus();
//            } catch (Exception e) {
//                try {
//                    errorMessage = context.getString(R.string.error_message_error_status, retrofitError.getResponse().getStatus());
//                } catch (Exception e1) {
//                    errorMessage = context.getString(R.string.error_message_unknown_error);
//                }
//            }
//        }
//    }
}
