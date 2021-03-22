package com.secure.newuserimpl;

import android.content.Context;

import com.secure.newuserimpl.requests.IUserRequest;
import com.secure.newuserimpl.requests.UserNetworkDBAddRecordRequest;
import com.secure.newuserimpl.requests.UserNetworkDBDeleteRecordByNameRequest;
import com.secure.newuserimpl.requests.UserNetworkDBGetAllRecordsRequest;
import com.secure.newuserimpl.requests.UserNetworkDBGetRecordsCountRequest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class UserDataNetDBImpl extends UserDataBaseImpl {

    @Override
    public void cancelRequest(IUserRequest userRequest) {
        super.cancelRequest(userRequest);
    }

    @Override
    public void init(Context context) {
        super.init(context);
    }

    private HttpURLConnection getConnection(String connURL, String method, Map<String, String> headers){
        HttpURLConnection connection = null;
        try {
            URL url = new URL(connURL);
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

    @Override
    public IUserRequest addRecord(UserRecord record, IUserDataCallBack callBack) {

        final IUserRequest request = new UserNetworkDBAddRecordRequest(IUserRequest.RequestType.REQUEST_ADD, record, callBack);
        executorService.submit(request);

        return request;
    }

    @Override
    public IUserRequest getRecordByName(String name, IUserDataCallBack callBack) {
        return null;
    }

    @Override
    public IUserRequest deleteRecordByName(String name, IUserDataCallBack callBack) {

        final IUserRequest request = new UserNetworkDBDeleteRecordByNameRequest(IUserRequest.RequestType.REQUEST_DELETE, callBack, name);

        return request;
    }

    @Override
    public IUserRequest getAllRecords(IUserDataCallBack callBack) {

        IUserRequest request = new UserNetworkDBGetAllRecordsRequest(IUserRequest.RequestType.REQUEST_GET_ALL, callBack);
        executorService.submit(request);

        return request;
    }

    @Override
    public IUserRequest getCount(IUserDataCallBack callBack) {

        IUserRequest request = new UserNetworkDBGetRecordsCountRequest(IUserRequest.RequestType.REQUEST_GET_COUNT, callBack);
        executorService.submit(request);
        return  request;

    }

}
