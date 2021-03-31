package com.secure.newuserimpl.requests;


import android.database.sqlite.SQLiteDatabase;

import com.secure.newuserimpl.IUserDataCallBack;
import com.secure.newuserimpl.SQLiteWrapper;

public class UserLocalDBDeleteRecordByNameRequest extends UserBaseRequest {

    private String deletedName;
    private SQLiteWrapper sqLiteWrapper;

    public UserLocalDBDeleteRecordByNameRequest(RequestType requestType, IUserDataCallBack callBack, String deletedName, SQLiteWrapper sqLiteWrapper) {
        super(requestType, callBack);
        this.deletedName = deletedName;
        this.sqLiteWrapper = sqLiteWrapper;
    }
    @Override
    public String getDeletedName() {
        return deletedName;
    }


    @Override
    public void run() {
        SQLiteDatabase db = sqLiteWrapper.getWritableDatabase();
        int count = db.delete(SQLiteWrapper.TABLE_RECORDS, SQLiteWrapper.KEY_NAME + " = ?", new String[] {deletedName});
        db.close();
        userResponse = new UserDeleteRecordByNameResponse(true, count);
        callBack.onResponse(this);
    }

}
