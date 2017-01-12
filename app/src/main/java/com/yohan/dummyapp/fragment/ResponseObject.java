package com.yohan.dummyapp.fragment;

/**
 * Created by Admin on 10-01-17.
 */

public class ResponseObject {
    private static final int STATUS_OK = 201;
    //private static final int STATUS_ERROR = ;

    int id;
    Result result;

    public class Result {
        boolean success;
        int status;
        Content content;
        ErrorContent errorContent;

    }

    public class Content {
        String data;
    }

    public class ErrorContent {
        String data;
    }
    public String getContent() {
        StringBuilder builder = new StringBuilder();
        builder.append("Response Code: ");
        builder.append(result.status);
        builder.append("\n");
        builder.append("Response: ");
        switch(result.status){
            case 201:
                builder.append(result.content.data);
                break;
            default:
                builder.append(result.errorContent.data);
        }
        return builder.toString();
    }
}