package com.secure.newuserimpl.requests;


import com.secure.newuserimpl.IUserDataCallBack;
import com.secure.newuserimpl.UserRecord;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserNetworkDBGetAllRecordsRequest extends UserNetworkBaseRequest {

    public UserNetworkDBGetAllRecordsRequest(RequestType requestType, IUserDataCallBack callBack) {
        super(requestType, callBack);
    }

    @Override
    public void run() {
        List<UserRecord> list = new ArrayList<>();
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
                int l = jsonArray.length();
                for (int index = 0 ;  index < l ; ++index) {
                    UserRecord record = fetchRecord((JSONObject) jsonArray.get(index));
                    list.add(record);
                }


                // print result
                System.out.println(response);
            } else {
                System.out.println("GET request not worked");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        userResponse = new UserGetAllRecordsResponse(true, list);
        callBack.onResponse(this);
    }


}
