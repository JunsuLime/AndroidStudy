package org.osori.androidstudy.week7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
 * 2) BindService, StartService
 * 3) SendBroadcast, ReceiveBroadcast
 * 4) LayoutInflate
 * 5) Get Device system service
 * 6) Access on Device storage
 */

public class WhatIsContextActivity extends AppCompatActivity {

    private Button startActivityButton;

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
}
