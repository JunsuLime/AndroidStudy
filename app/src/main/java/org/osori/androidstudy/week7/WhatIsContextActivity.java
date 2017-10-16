package org.osori.androidstudy.week7;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.osori.androidstudy.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by junsu on 2017-10-14.
 *
 * 어떤 식으로 알려주는 것이 좋을까?
 * 1. 간단한 Context 의 개념
 * 2. Context 로 무엇을 할 수 있는가?
 * 3. Context 깊이 탐구하기 (이건 시간 되면 하기)
 *
 * 1. 간단한 Context 의 개념
 *
 * Application 환경에 관한 정보를 접근하기 위해 필요한 interface 이다.
 *
 * 2. Context 로 무엇을 할 수 있는가?
 *
 * 1) StartActivity
 * 2) BindService, StartService - 나중에 이어서 하기
 * 3) SendBroadcast, ReceiveBroadcast
 * 4) LayoutInflate
 * 5) Get Device system service
 * 6) Access on Device storage
 * 7) Get resource (drawable, string, color)
 */

public class WhatIsContextActivity extends AppCompatActivity {

    private LinearLayout container;

    private Button startActivityButton;
    private Button sendBroadcastButton;
    private Button inflateButton;
    private Button getResourceButton;
    private Button accessStorageReadButton;
    private Button accessStorageWriteButton;
    private Button accessStorageDeleteButton;
    private Button getSystemServiceButton;

    private BroadcastReceiver receiver;

    private static final String ACTION_TEST = "osori.test_action";
    private static final String INTENT_TEST = "osori.test_key";

    private static final String FILE_NAME = "new_file.txt";

    private final int LOCATION_PERMISSION_REQUEST = 2017;
    private final int LOCATION_REQUEST_INTERVAL = 1000;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_context);

        startActivityButton = (Button) findViewById(R.id.context_start_activity);
        startActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity();
            }
        });

        sendBroadcastButton = (Button) findViewById(R.id.context_send_broadcast);
        sendBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMyBroadcast();
            }
        });

        // broadcast receiver 초기화
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(WhatIsContextActivity.this,
                        intent.getAction() + " !! " + String.valueOf(intent.getStringExtra(INTENT_TEST)), Toast.LENGTH_SHORT).show();
            }
        };
        // intent filter 는 어떤 action 의 intent 만 받을 지 걸러주는 filter 이다.
        // ACTION_TEST 를 addAction 하지 않고 test 해보자
        // broadcast 가 receive 되지 않을 것이다.
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TEST);
        registerReceiver(receiver, intentFilter);


        // inflate 한 layout 을 현재 layout 에 add 하기 위해 현재 layout 을 가져옴옴
        container = (LinearLayout) findViewById(R.id.what_is_context_container);

        inflateButton = (Button) findViewById(R.id.context_inflate_button);
        inflateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflateLayout();
            }
        });

        getResourceButton = (Button) findViewById(R.id.context_get_resource_button);
        getResourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStringResource();
            }
        });

        accessStorageReadButton = (Button) findViewById(R.id.context_get_device_storage_read_button);
        accessStorageWriteButton = (Button) findViewById(R.id.context_get_device_storage_write_button);
        accessStorageDeleteButton = (Button) findViewById(R.id.context_get_device_storage_delete_button);

        accessStorageReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readStorage();
            }
        });
        accessStorageWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeOnStorage();
            }
        });
        accessStorageDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFileAtStorage();
            }
        });

        getSystemServiceButton = (Button) findViewById(R.id.context_system_service_button);
        getSystemServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
                ActivityCompat.requestPermissions(WhatIsContextActivity.this, permissions, LOCATION_PERMISSION_REQUEST);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 꼭꼭!! 등록한 receiver 는 activity 가 완전히 destroy 되기 전에
        // unregister 해주어야 된다.
        unregisterReceiver(receiver);
    }

    private void startNewActivity() {
        /**
         * startActivity 함수 안으로 들어가보자
         * startActivity 는 override 된 method 임을 볼 수 있다.
         *
         * activity 가 상속 받은 class 를 계속 들어가다보면 Context 를 상속 받음을 확인 할 수 있다.
         * Context class 에서 startActivity 를 검색하면 abstract class 로 선언 되있음을
         * 알 수 있다.
         */

        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

    private void sendMyBroadcast() {
        // 이와 같이 action 에 extra 를 추가해서
        // 내가 원하는 정보를 첨부해 broadcast 를 날릴 수 있다.
        Intent intent = new Intent(ACTION_TEST);
        intent.putExtra(INTENT_TEST, "Hell World..");

        // sendBroadcast 도 아까 activity 에서 했던 것 처럼 탐색해보자
        // Context 에서 abstract 로 선언 된 method 임을 알 수 있다.
        sendBroadcast(intent);
    }

    private void inflateLayout() {
        // Activity 는 Context class 를 상속 받은 class 이다.
        // LayoutInflater.from( 에는 Context 가 들어가며 여기서 this 는 현재 class 즉
        // WhatIsContextActivity 의 object 를 말한다.
        View view = LayoutInflater.from(this).inflate(R.layout.item_inflate_test, null);

        // inflate 가 잘 되었음을 테스트 하기 위해 addView 까지 했다.
        container.addView(view);
    }

    private void getStringResource() {
        // 여기서는 string resource 가져오는 예제만 했지만
        // res 안에 선언 되어있는 모든 resource 를 가지고 올 수 있다.
        // "android drawable 가져오기" 이런 식으로 검색하면 다 나오니까 찾아서 쓰면 된다.

        // getString 또한 Context 가 가지고 있는 method 이다.
        String appName = getString(R.string.app_name);

        Toast.makeText(this, appName, Toast.LENGTH_SHORT).show();
    }

    private void readStorage() {
        // getExternalFilesDir method 는 Activity 가 가지고 있는 method 이며
        // Context 에 abstract method 로 정의 되어 있다.
        File docsDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(docsDir, FILE_NAME);

        if (file.exists()) {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader bReader = new BufferedReader(reader);

                String text = bReader.readLine();
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

                reader.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
        } else {
            Toast.makeText(this, "Write plz", Toast.LENGTH_SHORT).show();
        }
    }

    private void writeOnStorage() {
        File docsDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(docsDir, FILE_NAME);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("fee gone");
            Toast.makeText(this, "Success on Write", Toast.LENGTH_SHORT).show();
            writer.close();
        } catch (IOException e) {

        }
    }

    private void deleteFileAtStorage() {
        File docsDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(docsDir, FILE_NAME);

        if (file.exists()) {
            if (file.delete()) {
                Toast.makeText(this, "Success on delete", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failure on delete", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "There is no file", Toast.LENGTH_SHORT).show();
        }
    }

    private void getMySystemService() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Android Device system service 를 context 내의 getSystemService method 를 이용해서 가져올 수 있다.
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Toast.makeText(WhatIsContextActivity.this,
                String.format("lat: %f, lng: %f", location.getLatitude(), location.getLongitude()),
                Toast.LENGTH_SHORT).show();

//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REQUEST_INTERVAL, 0, new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                Toast.makeText(WhatIsContextActivity.this,
//                        String.format("lat: %f, lng: %f", location.getLatitude(), location.getLongitude()),
//                        Toast.LENGTH_SHORT).show();
//
//                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                locationManager.removeUpdates(this);
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//
//            }
//        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length == 0)
            return;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    getMySystemService();
                }
        }
    }
}
