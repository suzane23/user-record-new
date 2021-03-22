package com.secure.newuserimpl.requests;



import com.secure.newuserimpl.UserRecord;

import java.util.List;

public class UserGetRecordsByNameResponse extends UserBaseResponse{
    private List<UserRecord> list;

    public UserGetRecordsByNameResponse(boolean result, List<UserRecord> list) {
        super(result);
        this.list = list;
    }

    public List<UserRecord> getRecordList() {
        return list;
    }
}
