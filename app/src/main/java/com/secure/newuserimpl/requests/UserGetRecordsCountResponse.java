package com.secure.newuserimpl.requests;

public class UserGetRecordsCountResponse extends UserBaseResponse {
    private int count;

    public UserGetRecordsCountResponse(boolean result, int count) {
        super(result);
        this.count = count;
    }

    public int getCount(){
        return count;
    }
}
