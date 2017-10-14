package org.osori.androidstudy.week7;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by junsu on 2017-10-14.
 * [Context]
 * Application 에 관하여 시스템이 관리하고 있는 정보에 접근하기
 * Android System Service 에서 제공하는 API 를 호출 할 수 있는 기능
 *
 *
 * Android 의 Process 에 관하여 ...
 *
 * Android 는 Process 와 Application 이 따로 관리 된다.
 *
 * Process 는 Android kernel 에 의해 관리된다. 그렇다면 Application 은?
 *
 * Application 은 ActivityManagerService 라는 "Application" 에서 관리한다.
 * 그리고 Android 에서 Application 에 접근 할 때는 ActivityManagerService 를 통해 접근해야한다.
 *
 * ActivityManagerService 는 key-value 쌍으로 app 을 관리하고 있으며 application 에 접근하기
 * 위해서는 key value 가 필요하다.
 *
 * 그리고 Context 는 key-value 쌍의 key 이다.
 *
 * Context 는 id 카드와 같은 개념이다.???
 *
 * 자신의 권한을 제3자에게 넘겨주는 pendingIntent 와 같은 기능도 가능해진다.
 *
 * Q: Context 는 언제 생성되는가?
 * A: Application 이 생성될 때
 *
 * ?????? 책 읽고 다시 정리해보자
 *
 */

public class WhatIsContextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
