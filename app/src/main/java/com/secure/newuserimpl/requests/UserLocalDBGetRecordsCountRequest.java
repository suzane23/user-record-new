package com.secure.newuserimpl.requests;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.secure.newuserimpl.IUserDataCallBack;
import com.secure.newuserimpl.SQLiteWrapper;

public class UserLocalDBGetRecordsCountRequest extends UserBaseRequest{
    private SQLiteWrapper sqLiteWrapper;

    public UserLocalDBGetRecordsCountRequest(RequestType requestType, IUserDataCallBack callBack, SQLiteWrapper sqLiteWrapper) {
        super(requestType, callBack);
        this.sqLiteWrapper = sqLiteWrapper;
    }

    @Override
    public void run() {

        String selectQuery = "SELECT * FROM " + SQLiteWrapper.TABLE_RECORDS;
        SQLiteDatabase db = sqLiteWrapper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int count = cursor.getCount();

        userResponse = new UserGetRecordsCountResponse(true, count);
        callBack.onResponse(this);
    }

}
