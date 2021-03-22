package com.secure.newuserimpl.requests;


import com.secure.newuserimpl.IUserDataCallBack;

public abstract class UserBaseRequest implements IUserRequest {



    private Long requestID;
    private RequestType requestType;
    protected IUserDataCallBack callBack;

    protected IUserResponse userResponse;
//    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public UserBaseRequest(RequestType requestType, IUserDataCallBack callBack) {
        requestID = System.nanoTime();
        this.requestType = requestType;
        this.callBack = callBack;
    }



    @Override
    public void run() {

    }

    @Override
    public Long getRequestID() {
        return requestID;
    }

    @Override
    public RequestType getRequestType() {
        return requestType;
    }

    @Override
    public IUserDataCallBack getCallback() {
        return callBack;
    }

    @Override
    public void setResponse(IUserResponse userResponse) {
        this.userResponse = userResponse;
    }

    @Override
    public IUserResponse getResponse() {
        return userResponse;
    }

    @Override
    public void onComplete() {
        callBack.onResponse(this);
    }

//    @Override
//    public IUserRequest execute() {
//        executorService.submit(this);
//        return this;
//    }
}
