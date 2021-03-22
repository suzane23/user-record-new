package com.secure.newuserimpl;

import android.content.Context;

import com.secure.newuserimpl.requests.IUserRequest;


public interface IUserData {
      void init(Context context);
      IUserRequest addRecord(UserRecord record, IUserDataCallBack callBack);
      IUserRequest getRecordByName(String name, IUserDataCallBack callBack);
//      void getRecordById(String id, IUserDataCallBack callBack);
      IUserRequest getAllRecords(IUserDataCallBack callBack);
      IUserRequest getCount(IUserDataCallBack callBack);
      IUserRequest deleteRecordByName(String name, IUserDataCallBack callBack);

      void cancelRequest(IUserRequest userRequest);
}
