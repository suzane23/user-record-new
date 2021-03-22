package com.secure.newuserimpl.requests;


import com.secure.newuserimpl.IUserDataCallBack;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class UserNetworkDBDeleteRecordByNameRequest extends UserNetworkBaseRequest {


    private String deletedName;
    public UserNetworkDBDeleteRecordByNameRequest(RequestType requestType, IUserDataCallBack callBack, String deletedName) {
        super(requestType, callBack);
        this.deletedName = deletedName;
    }

    public String getDeletedName() {
        return deletedName;
    }


    @Override
    public void run() {
        int count = 0;
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put("x-apikey", "d56cd9b8b341a2883dfb6511098cbe994b7a6");
            headersMap.put("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", deletedName);
            String query = jsonObject.toString();

            String delete_url = String.format(DELETE_URL,query);

            HttpURLConnection connection = getConnection( delete_url,"DELETE", headersMap);

            int code = connection.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK) { // success
                String response = readResponse(connection);

                JSONObject object = new JSONObject(response);
                count = object.getInt("result");


                // print result
                System.out.println(response);
            } else {
                System.out.println("DELETE request not worked");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        userResponse = new UserDeleteRecordByNameResponse(true, count);
        callBack.onResponse(this);
    }


}
