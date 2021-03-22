package com.secure.newuserimpl;

public class RecordFactory {
    public enum RecordStorageType {
        LOCALDB,
        FILE,
        NETWORKDB;
    }

    public static IUserData getInstance(RecordStorageType recordStorageType){
        if(recordStorageType == RecordStorageType.LOCALDB) {
            return UserDataLocalDBImpl.getInstance();
        }
        else if(recordStorageType == RecordStorageType.NETWORKDB){
            return new UserDataNetDBImpl();
        }
        else return null;

    }
}
