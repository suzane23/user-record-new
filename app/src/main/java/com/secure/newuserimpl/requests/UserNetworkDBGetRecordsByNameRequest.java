package com.secure.newuserimpl.requests;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.secure.newuserimpl.IUserDataCallBack;
import com.secure.newuserimpl.SQLiteWrapper;
import com.secure.newuserimpl.UserRecord;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserNetworkDBGetRecordsByNameRequest extends UserNetworkBaseRequest {

    private String searchName;


    public UserNetworkDBGetRecordsByNameRequest(RequestType requestType, IUserDataCallBack callBack, String searchName) {
        super(requestType, callBack);
        this.searchName = searchName;
    }

    @Override
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    @Override
    public void run() {
        List<UserRecord> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("name", searchName);
            String query = jsonObject.toString();
            String getUrl = URL + "?q="+ query;
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put("x-apikey", "d56cd9b8b341a2883dfb6511098cbe994b7a6");
            headersMap.put("Content-Type", "application/json");

            HttpURLConnection connection = getConnection(getUrl,"GET", headersMap);

            int code = connection.getResponseCode();
            System.out.println(code);
            if (code == HttpURLConnection.HTTP_OK) {

                String response = readResponse(connection);

                JSONArray jsonArray = new JSONArray(response);
                int l = jsonArray.length();
                for (int index = 0 ;  index < l ; ++index) {
                    UserRecord record = fetchRecord((JSONObject) jsonArray.get(index));
                    list.add(record);
                }
                System.out.println(response);
            } else {
                System.out.println("GET request not worked");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        userResponse = new UserGetRecordsByNameResponse(true, list);
        callBack.onResponse(this);
    }


}
