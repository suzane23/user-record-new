package com.secure.newuserimpl.requests;

import com.secure.newuserimpl.IUserDataCallBack;

public interface IUserRequest extends Runnable{
    enum RequestType {
        REQUEST_ADD,
        REQUEST_DELETE,
        REQUEST_GET_BY_NAME,
        REQUEST_GET_ALL,
        REQUEST_GET_COUNT
    }

    Long getRequestID();
    RequestType getRequestType();
    IUserDataCallBack getCallback();

    void setResponse(IUserResponse userResponse);
    IUserResponse getResponse();

//    IUserRequest execute();
    void onComplete();
}
