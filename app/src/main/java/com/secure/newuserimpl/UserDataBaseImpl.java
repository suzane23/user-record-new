package com.secure.newuserimpl;

import android.content.Context;

import com.secure.newuserimpl.requests.IUserRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class UserDataBaseImpl implements IUserData{

    protected Context context = null;

    protected ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    public void init(Context context) {
        this.context = context;
    }


    @Override
    public void cancelRequest(IUserRequest userRequest) {

    }
}
