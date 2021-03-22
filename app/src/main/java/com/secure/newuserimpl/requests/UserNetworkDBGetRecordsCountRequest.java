package com.secure.newuserimpl.requests;


import com.secure.newuserimpl.IUserDataCallBack;

import org.json.JSONArray;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class UserNetworkDBGetRecordsCountRequest extends UserNetworkBaseRequest{
    public UserNetworkDBGetRecordsCountRequest(RequestType requestType, IUserDataCallBack callBack) {
        super(requestType, callBack);
    }

    @Override
    public void run() {
        int count = 0;
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put("x-apikey", "d56cd9b8b341a2883dfb6511098cbe994b7a6");
            headersMap.put("Content-Type", "application/json");

            HttpURLConnection connection = getConnection(URL,"GET", headersMap);

            int code = connection.getResponseCode();
            System.out.println(code);

            if (code == HttpURLConnection.HTTP_OK) { // success

                String response = readResponse(connection);

                JSONArray jsonArray = new JSONArray(response);
                count = jsonArray.length();

            } else {
                System.out.println("GET request not worked");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        userResponse = new UserGetRecordsCountResponse(true, count);
        callBack.onResponse(this);
    }



}
