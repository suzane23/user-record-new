package com.secure.newuserimpl.requests;


import com.secure.newuserimpl.IUserDataCallBack;
import com.secure.newuserimpl.UserRecord;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class UserNetworkDBAddRecordRequest extends UserNetworkBaseRequest {
    private static final String URL = "https://userrecords-ea32.restdb.io/rest/records";

    private UserRecord userRecord;

    public UserNetworkDBAddRecordRequest(RequestType requestType, UserRecord userRecord, IUserDataCallBack callBack) {
        super(requestType, callBack);
        this.userRecord = userRecord;
    }

    public UserRecord getUserRecord() {
        return userRecord;
    }

    public void setUserRecord(UserRecord userRecord) {
        this.userRecord = userRecord;
    }

    @Override
    public void run() {
        try {

            Map<String, String> headersMap = new HashMap<>();
            headersMap.put("x-apikey", "d56cd9b8b341a2883dfb6511098cbe994b7a6");
            headersMap.put("Content-Type", "application/json");

            HttpURLConnection connection = getConnection(URL,"POST", headersMap);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(userRecord.toString() + "\n");
            outputStreamWriter.flush();
            outputStreamWriter.close();

            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Add record : " + userRecord.toString());
            } else {
                System.out.println("POST request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        userResponse = new UserAddRecordResponse(true);

        callBack.onResponse(this);
    }
}
