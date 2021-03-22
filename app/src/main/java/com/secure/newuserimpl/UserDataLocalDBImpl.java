package com.secure.newuserimpl;

import android.content.Context;

import com.secure.newuserimpl.requests.IUserRequest;
import com.secure.newuserimpl.requests.UserGetRecordsByNameRequest;
import com.secure.newuserimpl.requests.UserLocalDBAddRecordRequest;
import com.secure.newuserimpl.requests.UserLocalDBDeleteRecordByNameRequest;
import com.secure.newuserimpl.requests.UserLocalDBGetAllRecordsRequest;
import com.secure.newuserimpl.requests.UserLocalDBGetRecordsCountRequest;

public class UserDataLocalDBImpl extends UserDataBaseImpl{

    private SQLiteWrapper sqLiteWrapper = null;

    private static IUserData instance;

    private UserDataLocalDBImpl() {

    }

    synchronized public static IUserData getInstance(){

        if(null == instance){
            instance = new UserDataLocalDBImpl();
        }

        return instance;
    }


    @Override
    public void init(Context context) {
        super.init(context);
        sqLiteWrapper = new SQLiteWrapper(context);
    }

    @Override
    public IUserRequest addRecord(UserRecord record, IUserDataCallBack callBack) {

        IUserRequest request = new UserLocalDBAddRecordRequest(IUserRequest.RequestType.REQUEST_ADD, record, callBack, sqLiteWrapper);
        executorService.submit(request);

        return request;

//        return new UserLocalDBAddRecordRequest(IUserRequest.RequestType.REQUEST_ADD, record, callBack, sqLiteWrapper).execute();

    }


    @Override
    public IUserRequest deleteRecordByName(String name, IUserDataCallBack callBack) {
        IUserRequest request = new UserLocalDBDeleteRecordByNameRequest(IUserRequest.RequestType.REQUEST_DELETE, callBack, name, sqLiteWrapper);
        executorService.submit(request);

        return request;
    }

    @Override
    public IUserRequest getRecordByName(String name, IUserDataCallBack callBack) {

        IUserRequest request = new UserGetRecordsByNameRequest(IUserRequest.RequestType.REQUEST_GET_BY_NAME, callBack, name, sqLiteWrapper);
        executorService.submit(request);

        return request;
    }

    @Override
    public IUserRequest getAllRecords(IUserDataCallBack callBack) {

        IUserRequest request = new UserLocalDBGetAllRecordsRequest(IUserRequest.RequestType.REQUEST_GET_ALL, callBack, sqLiteWrapper);
        executorService.submit(request);

        return request;
    }

    @Override
    public IUserRequest getCount(IUserDataCallBack callBack) {

        IUserRequest request = new UserLocalDBGetRecordsCountRequest(IUserRequest.RequestType.REQUEST_GET_COUNT, callBack, sqLiteWrapper);
        executorService.submit(request);
        return  request;

    }

}
