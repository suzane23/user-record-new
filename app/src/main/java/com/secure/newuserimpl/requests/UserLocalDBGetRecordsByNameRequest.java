package com.secure.newuserimpl.requests;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.secure.newuserimpl.IUserDataCallBack;
import com.secure.newuserimpl.SQLiteWrapper;
import com.secure.newuserimpl.UserRecord;

import java.util.ArrayList;
import java.util.List;

public class UserLocalDBGetRecordsByNameRequest extends UserBaseRequest {

    private String searchName;
    private SQLiteWrapper sqLiteWrapper;



    public UserLocalDBGetRecordsByNameRequest(RequestType requestType, IUserDataCallBack callBack, String searchName, SQLiteWrapper sqLiteWrapper) {
        super(requestType, callBack);
        this.searchName = searchName;
        this.sqLiteWrapper = sqLiteWrapper;
    }

    @Override
    public String getSearchName() {
        return searchName;
    }

    @Override
    public void run() {
        List<UserRecord> list = new ArrayList<>();

        SQLiteDatabase db = sqLiteWrapper.getReadableDatabase();
        Cursor cursor =  db.query(SQLiteWrapper.TABLE_RECORDS, null,
                SQLiteWrapper.KEY_NAME + "=?", new String[]{searchName}, null , null, null, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0 ){

            do {
                UserRecord record = new UserRecord();
                int index = cursor.getColumnIndex(SQLiteWrapper.KEY_ID);

                if(index > -1) {
                    record.id = cursor.getString(index);
                }

                index = cursor.getColumnIndex(SQLiteWrapper.KEY_NAME);

                if(index > -1) {
                    record.name = cursor.getString(index);
                }

                index = cursor.getColumnIndex(SQLiteWrapper.KEY_TIME);

                if(index > -1) {
                    record.time = Long.valueOf(cursor.getString(index));
                }
                list.add(record);

            }while (cursor.moveToNext());

        }

        userResponse = new UserGetRecordsByNameResponse(true, list);
        callBack.onResponse(this);
    }


}
