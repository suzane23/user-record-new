package com.secure.newuserimpl;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserDataController controller = new UserDataController(this, RecordFactory.RecordStorageType.LOCALDB);
        UserRecord record = new UserRecord("Kuldeep");
        UserRecord record1 = new UserRecord("Neha");
        UserRecord record2 = new UserRecord("Anvay");
        UserRecord record3 = new UserRecord("Mahesh");
        UserRecord record4 = new UserRecord("Rahul");
        UserRecord record5 = new UserRecord("Anju");
        UserRecord record6 = new UserRecord("Pushpa");
        UserRecord record7 = new UserRecord("Dharamveer");
        UserRecord record8 = new UserRecord("Vikas");
        UserRecord record9 = new UserRecord("Rupender");

//        controller.add(record);
//        controller.add(record1);
//        controller.add(record2);
//        controller.add(record3);
//        controller.add(record4);
//        controller.add(record5);
//        controller.add(record6);
//        controller.add(record7);
//        controller.add(record8);
//        controller.add(record9);
//        controller.getAllRecords();
//       controller.deleteRecord("Avika");
        controller.getRecordsByName("Shuchika");
//        controller.getRecordsCount();
    }
}