package com.secure.newuserimpl;


import com.secure.newuserimpl.requests.IUserRequest;

public interface IUserDataCallBack {
    void onResponse(IUserRequest userRequest);
}
