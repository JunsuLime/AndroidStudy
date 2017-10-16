package org.osori.androidstudy.week7;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
