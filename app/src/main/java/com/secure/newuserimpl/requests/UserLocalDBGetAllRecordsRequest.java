package com.secure.newuserimpl.requests;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.secure.newuserimpl.IUserDataCallBack;
import com.secure.newuserimpl.SQLiteWrapper;
import com.secure.newuserimpl.UserRecord;

import java.util.ArrayList;
import java.util.List;

public class UserLocalDBGetAllRecordsRequest extends UserBaseRequest {

    private SQLiteWrapper sqLiteWrapper;
    public UserLocalDBGetAllRecordsRequest(RequestType requestType, IUserDataCallBack callBack, SQLiteWrapper sqLiteWrapper) {
        super(requestType, callBack);
        this.sqLiteWrapper = sqLiteWrapper;
    }

    @Override
    public void run() {
        List<UserRecord> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + SQLiteWrapper.TABLE_RECORDS;
        SQLiteDatabase db = sqLiteWrapper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        cursor.moveToFirst();
        if (cursor!= null && cursor.getCount() > 0 ) {
            do {
                UserRecord userRecord = new UserRecord();

                int index = cursor.getColumnIndex(SQLiteWrapper.KEY_ID);

                if(index > -1) {
                    userRecord.id = cursor.getString(index);
                }

                index = cursor.getColumnIndex(SQLiteWrapper.KEY_NAME);

                if(index > -1) {
                    userRecord.name = cursor.getString(index);
                }

                index = cursor.getColumnIndex(SQLiteWrapper.KEY_TIME);

                if(index > -1) {
                    userRecord.time = Long.valueOf(cursor.getString(index));
                }


                // Adding contact to list
                contactList.add(userRecord);
            } while (cursor.moveToNext());
        }
        userResponse = new UserGetAllRecordsResponse(true, contactList);
        callBack.onResponse(this);
    }


}
