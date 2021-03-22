package com.secure.newuserimpl.requests;

import com.secure.newuserimpl.IUserDataCallBack;
import com.secure.newuserimpl.UserRecord;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class UserNetworkBaseRequest extends UserBaseRequest {

    protected static final String URL = "https://userrecords-ea32.restdb.io/rest/records";
    protected static final String DELETE_URL = "https://userrecords-ea32.restdb.io/rest/records/*?q=%s";

    public UserNetworkBaseRequest(RequestType requestType, IUserDataCallBack callBack) {
        super(requestType, callBack);
    }

    protected HttpURLConnection getConnection(String connURL, String method, Map<String, String> headers){
        HttpURLConnection connection = null;
        try {
            java.net.URL url = new URL(connURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            if(method.equals("POST")) {
                connection.setDoOutput(true);
            }

            if(null != headers) {
                Set<Map.Entry<String, String>> headerSet = headers.entrySet();

                for (Map.Entry<String, String> entry : headerSet) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;


    }

    protected String readResponse(HttpURLConnection connection){
        StringBuffer response = new StringBuffer();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    protected UserRecord fetchRecord(JSONObject jsonObject) {
        try {
            UserRecord record = new UserRecord();
            record.name = jsonObject.getString("name");
            record.id  = jsonObject.getString("id");
            record.time = jsonObject.getLong("time");
            return record;
        }
        catch (Exception e) {
            return  null;
        }
    }
}
