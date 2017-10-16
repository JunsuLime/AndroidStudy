package org.osori.androidstudy.week7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.osori.androidstudy.R;

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
 */

public class WhatIsContextActivity extends AppCompatActivity {

    private Button startActivityButton;
    private Button sendBroadcastButton;
    private BroadcastReceiver receiver;

    private static final String ACTION_TEST = "osori.test_action";
    private static final String INTENT_TEST = "osori.test_key";

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
}
