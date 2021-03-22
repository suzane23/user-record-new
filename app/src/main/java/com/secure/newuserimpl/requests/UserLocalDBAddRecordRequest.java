package com.secure.newuserimpl.requests;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.secure.newuserimpl.IUserDataCallBack;
import com.secure.newuserimpl.SQLiteWrapper;
import com.secure.newuserimpl.UserRecord;

public class UserLocalDBAddRecordRequest extends UserBaseRequest {

    private UserRecord userRecord;
    private  SQLiteWrapper sqLiteWrapper;

    public UserLocalDBAddRecordRequest(RequestType requestType, UserRecord userRecord, IUserDataCallBack callBack, SQLiteWrapper sqLiteWrapper) {
        super(requestType, callBack);
        this.userRecord = userRecord;
        this.sqLiteWrapper = sqLiteWrapper;
    }

    public UserRecord getUserRecord() {
        return userRecord;
    }

    public void setUserRecord(UserRecord userRecord) {
        this.userRecord = userRecord;
    }

    @Override
    public void run() {
        SQLiteDatabase db = sqLiteWrapper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteWrapper.KEY_ID, userRecord.id );
        values.put(SQLiteWrapper.KEY_NAME , userRecord.name);
        values.put(SQLiteWrapper.KEY_TIME , userRecord.time);
        db.insert(SQLiteWrapper.TABLE_RECORDS,null, values);
        System.out.println("Add record : " + userRecord.toString());
        db.close();

        callBack.onResponse(this);

    }
}
